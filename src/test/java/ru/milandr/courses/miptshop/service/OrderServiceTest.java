package ru.milandr.courses.miptshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.OrderDao;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.dtos.OrderProductDto;
import ru.milandr.courses.miptshop.entities.Order;
import ru.milandr.courses.miptshop.entities.OrderGood;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;
import ru.milandr.courses.miptshop.services.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Test(expected = ValidationException.class)
    public void nullGetTest() throws ValidationException {
        orderService.get(null);
    }

    @Test(expected = ValidationException.class)
    public void wrongIdGetTest() throws ValidationException {
        given(orderDao.findOne(1L)).willReturn(null);
        orderService.get(1L);
    }

    @Test
    public void getTest() throws ValidationException {
        LocalDateTime now = LocalDateTime.now();
        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 5);
        List<OrderGood> orderGoods = List.of(orderGood1, orderGood2);
        Order order = new Order(1L, 1L, OrderStatus.UNACCEPTED, now, orderGoods);

        given(orderDao.findOne(1L)).willReturn(order);

        OrderDto actualOrderDto = orderService.get(1L);

        OrderProductDto orderProductDto1 = new OrderProductDto(1L, "Product1", new BigDecimal(10), 10);
        OrderProductDto orderProductDto2 = new OrderProductDto(2L, "Product2", new BigDecimal(20), 5);
        List<OrderProductDto> orderProductDtos = List.of(orderProductDto1, orderProductDto2);
        OrderDto expectedOrderDto = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED, /*now,*/ orderProductDtos);

        assertThat(actualOrderDto).isEqualTo(expectedOrderDto);
    }

    @Test(expected = ValidationException.class)
    public void nullGetByUserIdTest() throws ValidationException {
        orderService.getListByUserId(null);
    }

    @Test(expected = ValidationException.class)
    public void wrongIdGetByUserIdTest() throws ValidationException {
        given(orderDao.findAllByUserId(1L)).willReturn(null);
        orderService.getListByUserId(null);
    }

    @Test
    public void getListByUserIdTest() throws ValidationException {
        LocalDateTime now = LocalDateTime.now();
        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 5);
        List<OrderGood> orderGoods1 = List.of(orderGood1, orderGood2);
        Order order1 = new Order(1L, 1L, OrderStatus.UNACCEPTED, now, orderGoods1);

        OrderGood orderGood3 = new OrderGood(2L, 1L, 15);
        OrderGood orderGood4 = new OrderGood(2L, 2L, 20);
        List<OrderGood> orderGoods2 = List.of(orderGood3, orderGood4);
        Order order2 = new Order(1L, 1L, OrderStatus.UNACCEPTED, now, orderGoods2);

        given(orderDao.findAllByUserId(1L)).willReturn(List.of(order1, order2));

        List<OrderDto> actualOrderDtoList = orderService.getListByUserId(1L);

        OrderProductDto orderProductDto1 = new OrderProductDto(1L, "Product1", new BigDecimal(10), 10);
        OrderProductDto orderProductDto2 = new OrderProductDto(2L, "Product2", new BigDecimal(20), 5);
        List<OrderProductDto> orderProductDtos1 = List.of(orderProductDto1, orderProductDto2);
        OrderDto expectedOrderDto1 = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED,/* now,*/ orderProductDtos1);

        OrderProductDto orderProductDto3 = new OrderProductDto(1L, "Product1", new BigDecimal(10), 15);
        OrderProductDto orderProductDto4 = new OrderProductDto(2L, "Product2", new BigDecimal(20), 20);
        List<OrderProductDto> orderProductDtos2 = List.of(orderProductDto3, orderProductDto4);
        OrderDto expectedOrderDto2 = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED, /*now, */orderProductDtos2);

        List<OrderDto> expectedOrderDtoList = List.of(expectedOrderDto1, expectedOrderDto2);

        assertThat(actualOrderDtoList).isEqualTo(expectedOrderDtoList);
    }

    @Test(expected = ValidationException.class)
    public void nullCreateTest() throws ValidationException {
        orderService.create(null);
    }

    @Test(expected = ValidationException.class)
    public void existingIdCreateTest() throws ValidationException {
        orderService.create(new OrderDto(1L, null, OrderStatus.UNACCEPTED, /*null, */null));
    }

    @Test(expected = ValidationException.class)
    public void noUserIdCreateTest() throws ValidationException {
        orderService.create(new OrderDto(null, null, OrderStatus.UNACCEPTED, /*null, */null));
    }

    @Test
    public void createTest() throws ValidationException {
        LocalDateTime now = LocalDateTime.now();
        OrderProductDto orderProductDto1 = new OrderProductDto(1L, "Product1", new BigDecimal(10), 10);
        OrderProductDto orderProductDto2 = new OrderProductDto(2L, "Product2", new BigDecimal(20), 5);
        List<OrderProductDto> orderProductDtos = List.of(orderProductDto1, orderProductDto2);
        OrderDto orderDto = new OrderDto(1L, OrderStatus.UNACCEPTED,/* now,*/ orderProductDtos);
        Order actualOrder = orderService.create(orderDto);
        //todo это хак, подумать, как лучше
        actualOrder.setId(1L);
        actualOrder.getOrderGoods().forEach(orderGood -> orderGood.setOrderId(actualOrder.getId()));

        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 5);
        List<OrderGood> orderGoods = List.of(orderGood1, orderGood2);
        Order expectedOrder = new Order(1L, 1L, OrderStatus.UNACCEPTED, now, orderGoods);


        assertThat(actualOrder).isEqualTo(expectedOrder);
    }
    //todo дописать для удаления и обновления
}