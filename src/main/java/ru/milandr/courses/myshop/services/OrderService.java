package ru.milandr.courses.myshop.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.OrderDao;
import ru.milandr.courses.myshop.daos.OrderGoodDao;
import ru.milandr.courses.myshop.dtos.OrderDto;
import ru.milandr.courses.myshop.dtos.OrderGoodDto;
import ru.milandr.courses.myshop.entities.Order;
import ru.milandr.courses.myshop.entities.OrderGood;
import ru.milandr.courses.myshop.entities.OrderGoodPK;
import ru.milandr.courses.myshop.utils.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.milandr.courses.myshop.utils.ValidationUtils.validateIsNotNullWithException;
import static ru.milandr.courses.myshop.utils.ValidationUtils.validateIsNullWithException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderDao orderDao;
    private final OrderGoodDao orderGoodDao;

    public OrderDto findOrder(Long orderId) {
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
                .map(orderGood -> {
                    OrderGoodDto orderGoodDto = new OrderGoodDto();
                    orderGood.setOrderId(orderGood.getOrderId());
                    orderGoodDto.setGoodId(orderGood.getGoodId());
                    orderGoodDto.setQuantity(orderGood.getQuantity());

                    return orderGoodDto;
                })
                .collect(Collectors.toList());
    }

    public void createOrder(OrderDto orderDto) throws BadRequestException {
        validateIsNotNullWithException(orderDto, "Null object can not be saved.");
        validateIsNullWithException(orderDto.getId(), "Can not create an object with presented id");

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

    public void doSomething() {
        OrderGood orderGood = orderGoodDao.findOne(new OrderGoodPK(14L, 1L));
        log.info(orderGood.toString());
    }
}