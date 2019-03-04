package ru.milandr.courses.myshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milandr.courses.myshop.dtos.UserDto;
import ru.milandr.courses.myshop.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
