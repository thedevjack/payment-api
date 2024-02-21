package com.thedev.paymentsystem.controller;


import com.thedev.paymentsystem.dto.UserRequest;
import com.thedev.paymentsystem.entity.User;
import com.thedev.paymentsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest) {

        User user = userRequest.toModel();
        User userSave = userService.registerUser(user);

        return ResponseEntity.ok().body(userSave);
    }

}
