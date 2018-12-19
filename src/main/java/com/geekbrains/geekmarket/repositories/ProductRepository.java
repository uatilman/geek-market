package com.geekbrains.geekmarket.repositories;

import com.geekbrains.geekmarket.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findOneByTitle(String title);
}
