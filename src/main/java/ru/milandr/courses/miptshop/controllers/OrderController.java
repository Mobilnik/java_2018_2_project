package ru.milandr.courses.miptshop.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.services.OrderService;
import ru.milandr.courses.miptshop.common.utils.ValidationException;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws ValidationException {
        return ResponseEntity.ok(orderService.get(orderId));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderListByUserId(@PathVariable Long userId) throws ValidationException {
        return ResponseEntity.ok(orderService.getListByUserId(userId));
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) throws ValidationException {
        orderService.create(orderDto);
    }
}
