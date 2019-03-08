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

import java.util.List;
import java.util.stream.Collectors;

import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNotNull;
import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNull;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderDao orderDao;

    public OrderDto getOrder(Long orderId) {
        Order order = orderDao.findOne(orderId);
        return buildOrderDtoFromOrder(order);
    }

    private OrderDto buildOrderDtoFromOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setUserId(order.getUserId());
        orderDto.setOrderGoods(buildOrderGoodDtoListFromOrderGoodList(order.getOrderGoods()));

        return orderDto;
    }

    private List<OrderGoodDto> buildOrderGoodDtoListFromOrderGoodList(List<OrderGood> orderGoods) {
        return orderGoods.stream()
                .map(orderGood -> new OrderGoodDto(orderGood.getGoodId(), orderGood.getQuantity()))
                .collect(Collectors.toList());
    }

    public void createOrder(OrderDto orderDto) throws ValidationException {
        validateIsNotNull(orderDto, "Null object can not be saved.");
        validateIsNull(orderDto.getId(), "Can not create an object with presented id");

        //todo validate that current user is equal to the one mentioned in order when Security added

        Order order = buildOrderFromOrderDto(orderDto);
        orderDao.save(order);

        order.setOrderGoods(buildOrderGoodListFromOrderDto(order, orderDto));
        orderDao.save(order);
    }


    private Order buildOrderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setStatus(orderDto.getStatus());
        order.setUserId(orderDto.getUserId());

        return order;
    }

    private List<OrderGood> buildOrderGoodListFromOrderDto(Order order, OrderDto orderDto) {
        List<OrderGoodDto> orderGoodDtos = orderDto.getOrderGoods();

        return orderGoodDtos.stream()
                .map(orderGoodDto -> {
                    OrderGood orderGood = new OrderGood();
                    //to prohibit sending a fake request
                    orderGood.setOrderId(order.getId());
                    orderGood.setGoodId(orderGoodDto.getGoodId());
                    orderGood.setQuantity(orderGoodDto.getQuantity());
                    return orderGood;
                })
                .collect(Collectors.toList());
    }
}