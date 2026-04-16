package com.assistx.controller;

import com.assistx.model.User;
import com.assistx.repository.UserRepository;
import com.assistx.dto.UpdateUsernameRequest;
import com.assistx.dto.UpdateEmailRequest;
import com.assistx.dto.ChangePasswordRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET PROFILE
    @GetMapping("/me")
    public User getProfile(Authentication auth) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        String email = auth.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));
    }

    // UPDATE USERNAME
    @PutMapping("/update-username")
    public User updateUsername(
            @RequestBody UpdateUsernameRequest request,
            Authentication auth
    ) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        if (request.username == null || request.username.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Username cannot be empty"
            );
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        // NEW: Prevent same username update
        if (user.getUsername().equals(request.username)) {
            return user;
        }

        // Prevent duplicate usernames
        if (userRepository.existsByUsername(request.username)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Username already exists"
            );
        }

        user.setUsername(request.username);

        return userRepository.save(user);
    }

    // UPDATE EMAIL
    @PutMapping("/update-email")
    public User updateEmail(
            @RequestBody UpdateEmailRequest request,
            Authentication auth
    ) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        if (request.email == null || request.email.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email cannot be empty"
            );
        }

        if (request.password == null || request.password.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password is required"
            );
        }

        String currentEmail = auth.getName();

        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        // Verify password
        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Incorrect password"
            );
        }

        // NEW: Prevent same email update
        if (user.getEmail().equals(request.email)) {
            return user;
        }

        // Prevent duplicate emails
        if (userRepository.existsByEmail(request.email)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already exists"
            );
        }

        user.setEmail(request.email);

        return userRepository.save(user);
    }

    // CHANGE PASSWORD
    @PutMapping("/change-password")
    public User changePassword(
            @RequestBody ChangePasswordRequest request,
            Authentication auth
    ) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        if (request.oldPassword == null ||
                request.newPassword == null ||
                request.confirmPassword == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "All fields are required"
            );
        }

        if (!request.newPassword.equals(request.confirmPassword)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords do not match"
            );
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        // Verify old password
        if (!passwordEncoder.matches(request.oldPassword, user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Incorrect old password"
            );
        }

        // Optional: prevent same password reuse
        if (passwordEncoder.matches(request.newPassword, user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "New password must be different"
            );
        }

        user.setPassword(passwordEncoder.encode(request.newPassword));

        return userRepository.save(user);
    }
}