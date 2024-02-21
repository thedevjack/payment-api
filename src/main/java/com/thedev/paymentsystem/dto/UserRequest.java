package com.thedev.paymentsystem.dto;

import com.thedev.paymentsystem.entity.User;

public record UserRequest(String name, String email, String password) {

    // transformar objeto UserRequest em User
    public User toModel() {
        return new User(name, email, password);
    }


}
