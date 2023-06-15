package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.data.UserData;
import com.example.demo.service.UserDataService;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("")
    public ResponseEntity<UserResponse> setUser(@RequestBody UserRequest req) {
        UserData data = userDataService.setUser(req.getUsername(), req.getAge());
        return this.getResponse(data, "Internal Server Error", 500);
    }

    @GetMapping("")
    public ResponseEntity<UserResponse> getUser(@RequestParam(value = "username") String username) {
        UserData data = userDataService.findUserByUsername(username);
        return this.getResponse(data, "User Not Found", 404);
    }

    @DeleteMapping("")
    public ResponseEntity<UserResponse> removeUser(@RequestParam(value = "username") String username) {
        UserData data = userDataService.removeUser(username);
        return this.getResponse(data, "User Not Found", 404);
    }

    private ResponseEntity<UserResponse> getResponse(UserData data, String message, int code) {
        UserResponse response = new UserResponse();
        response.setData(data);
        if (data != null) {
            response.setMessage("Operation successful");
            response.setCode(200);
        } else {
            response.setMessage(message);
            response.setCode(code);
        }
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}
