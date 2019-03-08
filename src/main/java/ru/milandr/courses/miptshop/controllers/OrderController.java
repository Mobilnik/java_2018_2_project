package ru.milandr.courses.miptshop.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.services.OrderService;
import ru.milandr.courses.miptshop.common.utils.ValidationException;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public OrderDto getUser(@PathVariable Long orderId) throws ValidationException {
        return orderService.get(orderId);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) throws ValidationException {
        orderService.create(orderDto);
    }
}
