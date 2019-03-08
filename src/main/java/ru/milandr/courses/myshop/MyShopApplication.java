package ru.milandr.courses.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.milandr.courses.myshop.services.OrderService;

@SpringBootApplication
public class MyShopApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MyShopApplication.class, args);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.doSomething();
    }
}
