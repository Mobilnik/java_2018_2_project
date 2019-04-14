package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.OrderDao;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.dtos.OrderProductDto;
import ru.milandr.courses.miptshop.entities.Order;
import ru.milandr.courses.miptshop.entities.OrderProduct;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNotNull;
import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNull;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderDao orderDao;

    public Order create(OrderDto orderDto) throws ValidationException {
        validateIsNotNull(orderDto, "No Order DTO provided");
        validateIsNull(orderDto.getId(), "Can not create an object with existing id");

        //todo validate that current user is equal to the one mentioned in order when Security added + test it
        Long userId = 1L;

        if (orderDto.getProducts() == null) {
            orderDto.setProducts(new ArrayList<>());
        }

        Order order = buildOrderFromOrderDto(orderDto);
        orderDao.save(order);

        order.setOrderProducts(buildOrderProductsFromOrderDto(order, orderDto));

        orderDao.save(order);
        return order;
    }

    public OrderDto get(Long orderId) throws ValidationException {
        validateIsNotNull(orderId, "No order id provided");

        Order order = orderDao.findOne(orderId);
        validateIsNotNull(order, "No order with id " + orderId);

        //todo проверить, что не пытаются посмотреть не свой заказ

        if (order.getOrderProducts() == null) {
            order.setOrderProducts(new ArrayList<>());
        }

        return buildOrderDto(order);
    }


    public OrderDto getCart() throws ValidationException {
        Order cartOrder = getOrderWithCartStatus();
        return buildOrderDto(cartOrder);
    }


    private OrderDto buildOrderDto(Order order) {
        return new OrderDto(order.getId(),
                order.getStatus(),
                //order.getChangeDateTime(),
                buildOrderProductDtos(order.getOrderProducts()),
                order.getComment());
    }


    private List<OrderProductDto> buildOrderProductDtos(List<OrderProduct> orderProducts) {
        if (orderProducts == null) {
            return new ArrayList<>();
        }

        List<OrderProductDto> orderProductDtos = orderProducts.stream()
                .map(orderProduct -> new OrderProductDto(
                        orderProduct.getProductId(),
                        orderProduct.getProduct().getName(),
                        orderProduct.getProduct().getPrice(),
                        orderProduct.getQuantity()))
                .collect(Collectors.toList());
        log.info(orderProductDtos.toString());
        return orderProductDtos;
    }

    private Order getOrderWithCartStatus() throws ValidationException {
        //todo get current user from context
        Long userId = 1L;

        List<Order> orders = orderDao.findByUserIdAndStatusCode(userId, OrderStatus.CART.getValue());
        if (orders.size() > 1) {
            throw new ValidationException("There is more than more than 1 order with status \"Cart\" ");
        } else if (orders.size() == 1) {
            return orders.get(0);

        }

        //Если не нашли заказа со статусом статусом, возвращаем пустой.
        Order order = new Order();
        order.setId(userId);

        return order;
    }


    private Order buildOrderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setStatus(orderDto.getStatus());
        order.setComment(orderDto.getComment());

        //todo validate that current user is equal to the one mentioned in order when Security added + test it
        Long userId = 1L;

        order.setUserId(userId);
        // order.setChangeDateTime(orderDto.getChangeDateTime());

        return order;
    }


    private List<OrderProduct> buildOrderProductsFromOrderDto(Order order, OrderDto orderDto) {
        List<OrderProductDto> orderProductDtos = orderDto.getProducts();

        if (orderProductDtos == null) {
            return new ArrayList<>();
        }

        return orderProductDtos.stream()
                .map(orderProductDto -> new OrderProduct(order.getId(),
                        orderProductDto.getProductId(),
                        orderProductDto.getQuantity()))
                .collect(Collectors.toList());
    }


    public List<OrderDto> getUserOrders() throws ValidationException {
        //todo validate that current user is equal to the one mentioned in order when Security added + test it
        Long userId = 1L;
        validateIsNotNull(userId, "No user id provided");

        List<Order> orders = orderDao.findAllByUserIdAndStatusCodeNot(userId, OrderStatus.CART.getValue());

        validateIsNotNull(orders, "No orders for user " + userId);

        return orders.stream()
                .map(this::buildOrderDto)
                .collect(Collectors.toList());
    }
}