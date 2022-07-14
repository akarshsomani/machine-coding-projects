package com.example.splitwise.controller;

import com.example.splitwise.request.FriendshipRequest;
import com.example.splitwise.request.UserRequest;
import com.example.splitwise.response.BaseResponse;
import com.example.splitwise.response.UserResponse;
import com.example.splitwise.service.FriendshipService;
import com.example.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friendship")
public class FriendshipController {


    @Autowired
    private FriendshipService friendshipService;



    @PostMapping("/create")
    public BaseResponse createFriend(@RequestBody FriendshipRequest friendshipRequest){
        return friendshipService.createFriend(friendshipRequest);

    }

}
