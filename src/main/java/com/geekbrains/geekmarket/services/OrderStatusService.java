package com.geekbrains.geekmarket.services;

import com.geekbrains.geekmarket.entities.OrderStatus;
import com.geekbrains.geekmarket.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public OrderStatus findById(Long id) {
        return orderStatusRepository.findById(id).orElse(null);
    }
}
