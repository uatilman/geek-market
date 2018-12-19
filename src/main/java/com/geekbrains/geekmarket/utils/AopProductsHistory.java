package com.geekbrains.geekmarket.utils;

import com.geekbrains.geekmarket.entities.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopProductsHistory {
    @After("execution(public * com.geekbrains.geekmarket.services.ProductService.saveProduct(..))")
    public void productSaveTrackerPointcut(JoinPoint joinPoint) {
        Product product = (Product)joinPoint.getArgs()[0];
        System.out.println("Сохрание товара: " + product.getTitle());
    }
}
