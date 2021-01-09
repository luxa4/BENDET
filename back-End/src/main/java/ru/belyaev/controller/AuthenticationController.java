/*
 * Created by Vologda Developer
 * Date: 23.06.2020
 * Time: 11:07
 */


package ru.belyaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.belyaev.entity.User;
import ru.belyaev.service.UserService;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public String checkRegistrationForm(@Valid @ModelAttribute("UserFromRegistrationForm") User user, BindingResult bindingResult) {
        User existUser = userService.findUserByName(user.getName());
        User existEmail = userService.findUserByEmail(user.getEmail());

        if (bindingResult.hasErrors())
            return "error";

        if ((existUser != null || existEmail != null) || (existUser != null && existEmail != null))
            return "User with this Name or Email already exists";

        userService.addUser(user);
        return "success";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
}

