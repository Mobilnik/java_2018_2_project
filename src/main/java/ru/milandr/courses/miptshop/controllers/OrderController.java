package ru.milandr.courses.miptshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.dtos.post.CreateOrderFromCartPostDto;
import ru.milandr.courses.miptshop.dtos.post.OrderProductPostDto;
import ru.milandr.courses.miptshop.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws ValidationException {
        return ResponseEntity.ok(orderService.get(orderId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getOrders() throws ValidationException {
        return ResponseEntity.ok(orderService.getUserOrders());
    }

    @GetMapping(value = "cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto getCart() throws ValidationException {
        return orderService.getUserCart();
    }

    @PostMapping(value = "create_from_cart")
    public void createFromCart(@RequestBody CreateOrderFromCartPostDto createOrderFromCartPostDto) throws ValidationException {
        orderService.createFromCart(createOrderFromCartPostDto);
    }

    @PostMapping(value = "create_cart_item")
    public void createCartItem(@RequestParam("productId") Long productId) throws ValidationException {
        orderService.createCartItem(productId);
    }

    @PostMapping(value = "update_cart_item")
    public void updateCartItem(@RequestBody OrderProductPostDto orderProductPostDto) throws ValidationException {
        orderService.updateCartItem(orderProductPostDto);
    }

    @PostMapping(value = "delete_cart_item")
    public void deleteCartItem(@RequestBody OrderProductPostDto orderProductPostDto) throws ValidationException {
        orderService.deleteCartItem(orderProductPostDto);
    }
}
