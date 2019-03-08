package ru.milandr.courses.miptshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.daos.UserDao;
import ru.milandr.courses.miptshop.dtos.UserDto;
import ru.milandr.courses.miptshop.entities.User;

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
    public UserDto getUser(Long userId) {
        User user = userDao.findOne(userId);
        return buildUserDtoFromUser(user);
    }

    private UserDto buildUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPhoto(user.getPhoto());

        return userDto;
    }

    public UserDto getUserByEmail(String userEmail) {
        User user = userDao.findByEmail(userEmail);
        return buildUserDtoFromUser(user);
    }
}