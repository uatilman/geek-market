package com.geekbrains.geekmarket.services.mail;

import com.geekbrains.geekmarket.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// TODO: 20.12.2018 вынести в отдельный микросервис
@Service
public class MailService {

    private JavaMailSender sender;
    private MailMessageBuilder messageBuilder;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    /**
     * Метод отвечает за непосредственную отпраку сообщения.
     * Отправка происходит в отдельно потоке.
     *
     * @param email адресат сообщения
     * @param subject тема сообщения
     * @param text
     */
    private void sendMail(String email, String subject, String text) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            executorService.submit(() -> sender.send(message));
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод извлекает из @param order адрес отправки сообщения,
     * формирует тему заказа, и из шаблонизатора получает текст сообщения
     *
     * @param order заказ, информация о котором отправляется в сообщении
     */
    public void sendOrderMail(Order order) {
        sendMail(
                order.getUser().getEmail(),
                String.format("Заказ %d%n отправлен в обработку", order.getId()),
                messageBuilder.buildOrderEmail(order));
    }

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    @Autowired
    public void setMessageBuilder(MailMessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }
}
