package ru.milandr.courses.myshop.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.myshop.dtos.OrderDto;
import ru.milandr.courses.myshop.services.OrderService;
import ru.milandr.courses.myshop.utils.BadRequestException;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public OrderDto getUser(@PathVariable Long orderId) {
        return orderService.findOrder(orderId);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) throws BadRequestException {
        orderService.createOrder(orderDto);
    }
}
