package com.online.survey.controllers;

import com.online.survey.dtos.UserDto;
import com.online.survey.models.UserModel;
import com.online.survey.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/get")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }
}
