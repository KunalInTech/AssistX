package com.assistx.service;

import com.assistx.dto.LoginRequest;
import com.assistx.dto.LoginResponse;
import com.assistx.dto.RegisterRequest;
import com.assistx.model.User;
import com.assistx.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterRequest request) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HHmmssddMMyy");

        long userId = Long.parseLong(
                LocalDateTime.now().format(formatter)
        );

        User user = new User();
        user.setUserId(userId);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // bcrypt later
        user.setRole(request.getRole());

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        return userRepository
                .findByEmailAndRole(request.getEmail(), request.getRole())
                .filter(user -> user.getPassword().equals(request.getPassword()))
                .map(user -> new LoginResponse(
                        true,
                        "Login successful",
                        user.getRole().name()
                ))
                .orElse(new LoginResponse(
                        false,
                        "Invalid credentials",
                        null
                ));
    }
}
