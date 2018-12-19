package com.geekbrains.geekmarket.controllers;

import com.geekbrains.geekmarket.entities.OrderItem;
import com.geekbrains.geekmarket.services.ShoppingCartService;
import com.geekbrains.geekmarket.utils.ShoppingCart;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.BinaryOperator;

@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart_items")
    public List<OrderItem> getAllCartOrderItems(HttpServletRequest httpServletRequest) {

        return shoppingCartService.getCurrentCart(httpServletRequest.getSession()).getItems();
    }

    @PostMapping("/cart_items")
    public int addProductToCartById(HttpServletRequest httpServletRequest, @RequestParam(name = "id") Long id) {
        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/cart_items")
    public int deleteProductFromCartById(HttpServletRequest httpServletRequest, @RequestParam(name = "id") Long id) {
        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
        return HttpStatus.OK.value();
    }

    @GetMapping("/cart_items/cost")
    public double getTotalCost(HttpServletRequest httpServletRequest) {
        return shoppingCartService.getTotalCost(httpServletRequest.getSession());
    }

    @PutMapping("/cart_items")
    public int setItemsCount(HttpServletRequest httpServletRequest, @RequestParam(name = "product_id") Long id, @RequestParam(name = "quantity") Long quantity) {
        shoppingCartService.setProductCount(httpServletRequest.getSession(), id, quantity);
        return HttpStatus.OK.value();
    }

//    @GetMapping("/cart/remove/{id}")
//    public String removeProductFromCartById(Model model, HttpServletRequest httpServletRequest, @PathVariable(name = "id") Long id) {
//        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
//        return "redirect:/shop/cart";
//    }
}
