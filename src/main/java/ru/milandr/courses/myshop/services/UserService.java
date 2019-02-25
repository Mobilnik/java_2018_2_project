package ru.milandr.courses.myshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.UserDao;
import ru.milandr.courses.myshop.entities.User;

import javax.transaction.Transactional;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // Transactional Нужна, если мы используем не контроллер, а достаем бин из контекста
    // без транзакции маппинги сущностей работать не будут. В данном случае
    // будет LazyInitializationException - orders не проинициализируется
    // @Transactional
    // При доступе из контроллера же неявно откроется транзакция на уровне запроса
    public User findUser(Long userId){
        User user = userDao.findOne(userId);
        System.out.println(user.toString());
        return user;
    }
}
