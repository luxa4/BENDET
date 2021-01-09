package ru.belyaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belyaev.entity.User;
import ru.belyaev.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/user")
    public User showUserInformation() {
        User user = userService.getUser();
        return user;
    }
}
