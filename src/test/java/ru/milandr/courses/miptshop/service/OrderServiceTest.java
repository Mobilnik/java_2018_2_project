package ru.milandr.courses.miptshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milandr.courses.miptshop.daos.OrderDao;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.dtos.OrderGoodDto;
import ru.milandr.courses.miptshop.entities.Order;
import ru.milandr.courses.miptshop.entities.OrderGood;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;
import ru.milandr.courses.miptshop.services.OrderService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderDao orderDao;

    private OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.orderService = new OrderService(orderDao);
    }

    @Test
    public void findOrderTest() {
        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 10);
        List<OrderGood> orderGoods = List.of(orderGood1, orderGood2);
        Order order = new Order(1L, 1L, OrderStatus.UNACCEPTED, orderGoods);

        given(orderDao.findOne(1L)).willReturn(order);

        OrderDto actualOrderDto = orderService.getOrder(1L);

        OrderGoodDto orderGoodDto1 = new OrderGoodDto(1L, 10);
        OrderGoodDto orderGoodDto2 = new OrderGoodDto(2L, 10);
        List<OrderGoodDto> orderGoodDtos = List.of(orderGoodDto1, orderGoodDto2);
        OrderDto expectedOrderDto = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED, orderGoodDtos);

        assertThat(actualOrderDto).isEqualTo(expectedOrderDto);
    }
}
