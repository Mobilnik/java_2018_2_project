package ru.milandr.courses.myshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milandr.courses.myshop.entities.User;
import ru.milandr.courses.myshop.services.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public User getUser(@PathVariable Long orderId){
        orderService.findOrder(orderId);
        return null;
    }
}
