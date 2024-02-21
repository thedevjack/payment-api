package com.thedev.paymentsystem.controller;


import com.thedev.paymentsystem.dto.UserRequest;
import com.thedev.paymentsystem.dto.UserResponse;
import com.thedev.paymentsystem.entity.User;
import com.thedev.paymentsystem.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {

        User user = userRequest.toModel();
        UserResponse userSave = userService.registerUser(user);

        return ResponseEntity.ok().body(userSave);
    }

    @GetMapping("/verify")
    public String verifyUser(@PathParam("code") String code) {
        if (userService.verify(code)) {
            return "Foda-se";
        } else {
            return "Verify_failed";
        }
    }

}
