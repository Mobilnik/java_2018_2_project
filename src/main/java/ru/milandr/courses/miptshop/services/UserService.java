package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.UserDao;
import ru.milandr.courses.miptshop.dtos.UserCreateDto;
import ru.milandr.courses.miptshop.dtos.UserDto;
import ru.milandr.courses.miptshop.entities.User;

import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNotNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    // Transactional Нужна, если мы используем не контроллер, а достаем бин из контекста
    // без транзакции маппинги сущностей работать не будут. В данном случае
    // будет LazyInitializationException - orders не проинициализируется
    // @Transactional
    // При доступе из контроллера же неявно откроется транзакция на уровне запроса
    public UserDto get(Long userId) throws ValidationException {
        validateIsNotNull(userId, "No User id provided");

        User user = userDao.findOne(userId);
        validateIsNotNull(user, "No User with id " + userId);

        return buildUserDtoFromUser(user);
    }

    public UserDto getByEmail(String email) throws ValidationException {
        validateIsNotNull(email, "No User email provided");

        User user = userDao.findByEmail(email);
        validateIsNotNull(user, "No User with email " + email);

        return buildUserDtoFromUser(user);
    }

    public UserDto getByName(String name) throws ValidationException {
        validateIsNotNull(name, "No username provided");

        User user = userDao.findByName(name);
        validateIsNotNull(user, "No User with name " + name);

        return buildUserDtoFromUser(user);
    }

    private UserDto buildUserDtoFromUser(User user) {
        return new UserDto(user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhoto());
    }

    public User create(UserCreateDto userCreateDto) {
       // User user = buildUserFromUserCreateDto(userCreateDto);
     //   user.setPasswordHash();
        return null;
    }

}