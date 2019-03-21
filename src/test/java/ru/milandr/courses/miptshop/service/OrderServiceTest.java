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
        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 5);
        List<OrderGood> orderGoods = List.of(orderGood1, orderGood2);
        Order order = new Order(1L, 1L, OrderStatus.UNACCEPTED, orderGoods);

        given(orderDao.findOne(1L)).willReturn(order);

        OrderDto actualOrderDto = orderService.get(1L);

        OrderGoodDto orderGoodDto1 = new OrderGoodDto(1L, 10);
        OrderGoodDto orderGoodDto2 = new OrderGoodDto(2L, 5);
        List<OrderGoodDto> orderGoodDtos = List.of(orderGoodDto1, orderGoodDto2);
        OrderDto expectedOrderDto = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED, orderGoodDtos);

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
        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 5);
        List<OrderGood> orderGoods1 = List.of(orderGood1, orderGood2);
        Order order1 = new Order(1L, 1L, OrderStatus.UNACCEPTED, orderGoods1);

        OrderGood orderGood3 = new OrderGood(2L, 1L, 15);
        OrderGood orderGood4 = new OrderGood(2L, 2L, 20);
        List<OrderGood> orderGoods2 = List.of(orderGood3, orderGood4);
        Order order2 = new Order(1L, 1L, OrderStatus.UNACCEPTED, orderGoods2);

        given(orderDao.findAllByUserId(1L)).willReturn(List.of(order1, order2));

        List<OrderDto> actualOrderDtoList = orderService.getListByUserId(1L);

        OrderGoodDto orderGoodDto1 = new OrderGoodDto(1L, 10);
        OrderGoodDto orderGoodDto2 = new OrderGoodDto(2L, 5);
        List<OrderGoodDto> orderGoodDtos1 = List.of(orderGoodDto1, orderGoodDto2);
        OrderDto expectedOrderDto1 = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED, orderGoodDtos1);

        OrderGoodDto orderGoodDto3 = new OrderGoodDto(1L, 15);
        OrderGoodDto orderGoodDto4 = new OrderGoodDto(2L, 20);
        List<OrderGoodDto> orderGoodDtos2 = List.of(orderGoodDto3, orderGoodDto4);
        OrderDto expectedOrderDto2 = new OrderDto(1L, 1L, OrderStatus.UNACCEPTED, orderGoodDtos2);

        List<OrderDto> expectedOrderDtoList = List.of(expectedOrderDto1, expectedOrderDto2);

        assertThat(actualOrderDtoList).isEqualTo(expectedOrderDtoList);
    }

    @Test(expected = ValidationException.class)
    public void nullCreateTest() throws ValidationException {
        orderService.create(null);
    }

    @Test(expected = ValidationException.class)
    public void existingIdCreateTest() throws ValidationException {
        orderService.create(new OrderDto(1L, null, OrderStatus.UNACCEPTED, null));
    }

    @Test(expected = ValidationException.class)
    public void noUserIdCreateTest() throws ValidationException {
        orderService.create(new OrderDto(null, null, OrderStatus.UNACCEPTED, null));
    }

    @Test
    public void createTest() throws ValidationException {

        OrderGoodDto orderGoodDto1 = new OrderGoodDto(1L, 10);
        OrderGoodDto orderGoodDto2 = new OrderGoodDto(2L, 5);
        List<OrderGoodDto> orderGoodDtos = List.of(orderGoodDto1, orderGoodDto2);
        OrderDto orderDto = new OrderDto(1L, OrderStatus.UNACCEPTED, orderGoodDtos);
        Order actualOrder = orderService.create(orderDto);
        //todo это хак, подумать, как лучше
        actualOrder.setId(1L);
        actualOrder.getOrderGoods().forEach(orderGood -> orderGood.setOrderId(actualOrder.getId()));

        OrderGood orderGood1 = new OrderGood(1L, 1L, 10);
        OrderGood orderGood2 = new OrderGood(1L, 2L, 5);
        List<OrderGood> orderGoods = List.of(orderGood1, orderGood2);
        Order expectedOrder = new Order(1L, 1L, OrderStatus.UNACCEPTED, orderGoods);


        assertThat(actualOrder).isEqualTo(expectedOrder);
    }
    //todo дописать для удаления и обновления
}