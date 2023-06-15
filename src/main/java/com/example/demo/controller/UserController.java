package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.data.UserData;
import com.example.demo.service.UserDataService;
import com.example.demo.dto.UserRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("")
    public UserData setUser(@RequestBody UserRequest req) {
        return userDataService.setUser(req.getUsername(), req.getAge());
    }

    @GetMapping("")
    public UserData getUser(@RequestParam(value = "username") String username) {
        return userDataService.findUserByUsername(username);
    }

    @DeleteMapping("")
    public boolean removeUser(@RequestParam(value = "username") String username) {
        return userDataService.removeUser(username);
    }
}
