package com.geekbrains.geekmarket.utils;

import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ShoppingCart {
    private List<OrderItem> items;
    private Double totalCost;

    public ShoppingCart() {
        items = new ArrayList<>();
        totalCost = 0.0;
    }

    public void add(Product product) {
        OrderItem orderItem = items.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(0L);
            orderItem.setId(0L);
            orderItem.setTotalPrice(0.0);
            items.add(orderItem);
        }
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        recalculate();
    }

    public void setQuantity(Product product, Long qnt) {
        OrderItem orderItem = items.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        if (orderItem == null) {
            return;
        }
        orderItem.setQuantity(qnt);
        recalculate();
    }

    public void remove(Product product) {
        OrderItem orderItem = items.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        if (orderItem == null) {
            return;
        }
        items.remove(orderItem);
        recalculate();
    }

    public void recalculate() {
        totalCost = 0.0;
        for (OrderItem o : items) {
            o.setTotalPrice(o.getQuantity() * o.getProduct().getPrice());
            totalCost += o.getTotalPrice();
        }
    }
}
