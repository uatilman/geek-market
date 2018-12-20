package com.geekbrains.geekmarket.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "price")
    private Double price;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @CreationTimestamp
    private LocalDateTime updateAt;

    /**
     * Поле добавлено на перспективу,
     * может служить уникалиным идентификатором страницы заказа для незарегистрированных пользователей.
     * По данному полю можно формировать уникальную ссылку и вставлять ее в письмо заказа.
     */
    @Column(name = "order_code")
    private String order_code;

}