package ru.milandr.courses.miptshop.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.common.utils.ValidationUtils;
import ru.milandr.courses.miptshop.dtos.UserDto;
import ru.milandr.courses.miptshop.dtos.post.CreateUserPostDto;
import ru.milandr.courses.miptshop.services.UserService;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    //form post conflicts with @RequestBody
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void create(@RequestParam Map<String, String> body) throws ValidationException {
        ValidationUtils.validateIsNotNull(body, "No params provided");
        String email = body.get("email");
        ValidationUtils.validateIsNotNull(email, "No param 'email' provided");
        String name = body.get("name");
        ValidationUtils.validateIsNotNull(name, "No param 'name' provided");
        String password = body.get("password");
        ValidationUtils.validateIsNotNull(password, "No param 'password' provided");


        userService.create(new CreateUserPostDto(email, name, password));
    }

    @GetMapping("current")
    public UserDto getCurrent() throws ValidationException {
        return userService.getCurrent();
    }
}