package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.Order;
import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.entities.OrderStatus;
import com.geekbrains.geekmarket.entities.User;
import com.geekbrains.geekmarket.repositories.OrderRepository;
import com.geekbrains.geekmarket.services.mail.MailService;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private MailService mailService;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        OrderStatus os = new OrderStatus(); // todo исправить
        os.setId(1L);
        os.setTitle("Сформирован");
        order.setStatus(os);
        order.setPrice(cart.getTotalCost());
        order = orderRepository.save(order);
        order.setOrderItems(new ArrayList<>(cart.getItems()));
        for (OrderItem o : cart.getItems()) {
            o.setOrder(order);
        }

        order.setOrder_code(UUID.randomUUID().toString());
        order = orderRepository.save(order);
        mailService.sendOrderMail(order);

        return order;
    }
}
