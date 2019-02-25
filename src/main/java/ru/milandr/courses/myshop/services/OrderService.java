package ru.milandr.courses.myshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.OrderDao;

import javax.transaction.Transactional;

@Service
public class OrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void demoMethod() {
    }
}
