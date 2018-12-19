package com.geekbrains.geekmarket.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    // INSERT INTO products (category_id, vendor_code, image, title, short_description, full_description, price)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @NotNull(message = "категория не выбрана")
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name = "vendor_code")
    @NotNull(message = "не может быть пустым")
    @Pattern(regexp = "([0-9]{1,})", message = "недопустимый символ")
    @Size(min = 8, max=8, message = "требуется 8 числовых символов")
    private String vendorCode;

    @Column(name = "image")
    private String imagePath;

    @Column(name = "title")
    @NotNull(message = "не может быть пустым")
    @Size(min = 5, max=250, message = "требуется минимум 5 символов")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "price")
    @NotNull(message = "не может быть пустым")
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    @Digits(integer=10, fraction=2)
    private double price;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
