package ru.milandr.courses.myshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.OrderDao;
import ru.milandr.courses.myshop.entities.Order;

@Service
public class OrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order findOrder(Long orderId) {
        Order order = orderDao.findOne(orderId);
        System.out.println(order.toString());
        return order;
    }
}
