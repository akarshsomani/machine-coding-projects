package com.example.splitwise.controller;

import com.example.splitwise.request.UserRequest;
import com.example.splitwise.response.UserResponse;
import com.example.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);

    }

}
