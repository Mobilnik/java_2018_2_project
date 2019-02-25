package ru.milandr.courses.myshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.UserDao;
import ru.milandr.courses.myshop.entities.User;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void demoMethod() {
        User user = new User();
        userDao.save(user);
    }
}
