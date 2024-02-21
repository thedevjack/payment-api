package com.thedev.paymentsystem.service;


import com.thedev.paymentsystem.dto.UserResponse;
import com.thedev.paymentsystem.entity.User;
import com.thedev.paymentsystem.repository.UserRepository;
import com.thedev.paymentsystem.utils.RandomString;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        List<User> getAll = userRepository.findAll();
        return getAll;
    }

    public UserResponse registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email already exists!");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            User saveUser = userRepository.save(user);

            UserResponse userResponse = new UserResponse(
                    saveUser.getId(),
                    saveUser.getName(),
                    saveUser.getEmail(),
                    saveUser.getPassword()
            );

            return userResponse;
        }
    }

}
