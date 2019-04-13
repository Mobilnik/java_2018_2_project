package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.OrderDao;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.dtos.OrderGoodDto;
import ru.milandr.courses.miptshop.entities.Order;
import ru.milandr.courses.miptshop.entities.OrderGood;
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

        validateIsNotNull(orderDto.getUserId(), "No user specified for the order");
        if (orderDto.getOrderGoods() == null) {
            orderDto.setOrderGoods(new ArrayList<>());
        }

        //todo validate that current user is equal to the one mentioned in order when Security added + test it

        Order order = buildOrderFromOrderDto(orderDto);
        orderDao.save(order);

        order.setOrderGoods(buildOrderGoodListFromOrderDto(order, orderDto));

        orderDao.save(order);
        return order;
    }

    public OrderDto get(Long orderId) throws ValidationException {
        validateIsNotNull(orderId, "No order id provided");

        Order order = orderDao.findOne(orderId);
        validateIsNotNull(order, "No order with id " + orderId);

        //todo проверить, что не пытаются посмотреть не свой заказ

        if (order.getOrderGoods() == null) {
            order.setOrderGoods(new ArrayList<>());
        }

        return buildOrderDtoFromOrder(order);
    }


    public OrderDto getCart() throws ValidationException {
        Order cartOrder = getOrderWithCartStatus();
        return buildOrderDtoFromOrder(cartOrder);
    }


    private OrderDto buildOrderDtoFromOrder(Order order) {
        return new OrderDto(order.getId(),
                order.getUserId(),
                order.getStatus(),
                //order.getChangeDateTime(),
                buildOrderGoodDtoListFromOrderGoodList(order.getOrderGoods()));
    }


    private List<OrderGoodDto> buildOrderGoodDtoListFromOrderGoodList(List<OrderGood> orderGoods) {
        if (orderGoods == null) {
            return new ArrayList<>();
        }

        return orderGoods.stream()
                .map(orderGood -> new OrderGoodDto(orderGood.getGoodId(), orderGood.getQuantity()))
                .collect(Collectors.toList());
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
        order.setUserId(orderDto.getUserId());
        // order.setChangeDateTime(orderDto.getChangeDateTime());

        return order;
    }


    private List<OrderGood> buildOrderGoodListFromOrderDto(Order order, OrderDto orderDto) {
        List<OrderGoodDto> orderGoodDtos = orderDto.getOrderGoods();

        if (orderGoodDtos == null) {
            return new ArrayList<>();
        }

        return orderGoodDtos.stream()
                .map(orderGoodDto -> new OrderGood(order.getId(),
                        orderGoodDto.getGoodId(),
                        orderGoodDto.getQuantity()))
                .collect(Collectors.toList());
    }


    public List<OrderDto> getListByUserId(Long userId) throws ValidationException {
        validateIsNotNull(userId, "No user id provided");

        List<Order> orders = orderDao.findAllByUserId(userId);
        validateIsNotNull(orders, "No orders for user " + userId);

        //todo validate that current user is equal to the one mentioned in order when Security added + test it

        return orders.stream()
                .map(this::buildOrderDtoFromOrder)
                .collect(Collectors.toList());
    }
}