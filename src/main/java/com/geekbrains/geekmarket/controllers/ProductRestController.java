package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.Product;
import com.geekbrains.geekmarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path="/products", method=RequestMethod.POST)
    public List<Product> postAllProducts(@RequestBody String postPayload){
        System.out.println(postPayload);
        return productService.getAllProducts();
    }

    @RequestMapping(path="/products", method=RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProductById(HttpServletRequest request, @PathVariable("id") Long id){
        System.out.println(request.getAttributeNames());
        return productService.getProductById(id);
    }
}
