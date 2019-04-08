package ru.milandr.courses.miptshop.controllers;

import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.dtos.UserDto;
import ru.milandr.courses.miptshop.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public UserDto get(@PathVariable Long userId) throws ValidationException {
        return userService.get(userId);
    }

    @GetMapping("email/{email}")
    public UserDto getByEmail(@PathVariable String email) throws ValidationException {
        return userService.getByEmail(email);
    }

    @GetMapping("name/{name}")
    public UserDto getByName(@PathVariable String name) throws ValidationException {
        return userService.getByName(name);
    }
}
