package com.geekbrains.geekmarket.services.mail;

import com.geekbrains.geekmarket.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailMessageBuilder {

    /**
     * Атрибут заказа
     */
    private static final String VARIABLE_ORDER = "order";
    /**
     * Название шаблона thymeleaf, из которого формируется почтовое сообщение
     */
    private static final String MAIL_ORDER_PAGE = "order-mail";

    private TemplateEngine templateEngine;

    /**
     * Метод формирует из шаблона thymeleaf MAIL_ORDER_PAGE итоговую html страницу.
     *
     * @param order заказ, который сделал пользователь, используются для рендеринга html страницы сообщения
     * и для извлечения данных о пользователе.
     * Для добавления атрибута VARIABLE_ORDER на thymeleaf шаблон используется контекст приложения.
     * @return html страницу, вствляемую в почтовое сообщение
     */
    public String buildOrderEmail(Order order) {
        Context context = new Context();
        context.setVariable(VARIABLE_ORDER, order);
        String result = templateEngine.process(MAIL_ORDER_PAGE, context);
        System.out.println(result);
        return result;
    }

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}
