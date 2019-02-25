package ru.milandr.courses.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.milandr.courses.myshop.services.GoodService;
import ru.milandr.courses.myshop.services.OrderService;
import ru.milandr.courses.myshop.services.UserService;

@SpringBootApplication
public class MyShopApplication {

    public static void main(String[] args) {
        ApplicationContext  context = SpringApplication.run(MyShopApplication.class, args);
//        UserService userService = context.getBean(UserService.class);
//        userService.findUser(1L);
    }

}
