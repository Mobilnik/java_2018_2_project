package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.PasswordUtils;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.common.utils.ValidationUtils;
import ru.milandr.courses.miptshop.daos.UserDao;
import ru.milandr.courses.miptshop.dtos.UserDto;
import ru.milandr.courses.miptshop.dtos.post.CreateUserPostDto;
import ru.milandr.courses.miptshop.entities.User;

import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNotNull;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

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

    public User create(CreateUserPostDto createUserPostDto) throws ValidationException {
        ValidationUtils.validateIsNotNull(createUserPostDto, "No user provided to proceed");

        User user = buildUserFromCreateUserDto(createUserPostDto);
        ValidationUtils.validateIsNotNull(user, "Can non handle request properly");

        User dbUser = userDao.findByName(user.getName());
        ValidationUtils.validateIsNull(dbUser, "User name is already in use");
        dbUser = userDao.findByEmail(user.getEmail());
        ValidationUtils.validateIsNull(dbUser, "User e-mail is already used");

        return userDao.save(user);
    }

    private User buildUserFromCreateUserDto(CreateUserPostDto createUserPostDto) {
        User user = new User();
        user.setEmail(createUserPostDto.getEmail());
        user.setName(createUserPostDto.getName());

        try {
            String salt = PasswordUtils.getNewSalt();
            String passwordHash = PasswordUtils.getPasswordHash(createUserPostDto.getPassword(), salt);

            user.setPasswordSalt(salt);
            user.setPasswordHash(passwordHash);
            return user;
        } catch (Exception e) {
            log.error("An exception occurred during salt generation");
            return null;
        }
    }

    public long getCurrentAuthenticatedUserIdSafely() throws ValidationException {
        User currentAuthenticatedUser = getCurrentAuthenticatedUser();
        ValidationUtils.validateIsNotNull(currentAuthenticatedUser, "Current user is not presented");

        return currentAuthenticatedUser.getId();
    }

    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName == null) {
            return null;
        }

        return userDao.findByName(currentPrincipalName);
    }

    public UserDto getCurrent() throws ValidationException {
        User currentUser = getCurrentAuthenticatedUser();
        ValidationUtils.validateIsNotNull(currentUser, "No current user");

        return buildUserDtoFromUser(currentUser);
    }
}