package com.thedev.paymentsystem.dto;

import com.thedev.paymentsystem.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotNull(message = "Nome não pode ser null")
        @NotBlank(message = "Campo não pode ser vazio")
        @Size(min = 2, max = 50)
        String name,

        @NotNull(message = "e-mail não pode ser null")
        @NotBlank(message = "Campo não pode ser vazio")
        @Email
        String email,

        @NotNull(message = "Senha não pode ser null")
        @NotBlank(message = "Campo não pode ser vazio")
        @Size(min = 8, max = 255, message = "A senha deve conter no minimo 8 caracteres")
        String password
) {

    // transformar objeto UserRequest em User
    public User toModel() {
        return new User(name, email, password);
    }


}
