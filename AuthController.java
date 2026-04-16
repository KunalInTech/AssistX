package com.assistx.controller;

import com.assistx.dto.LoginRequest;
import com.assistx.dto.LoginResponse;
import com.assistx.dto.RegisterRequest;
import com.assistx.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    // LOGIN (JWT)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // VERIFY ADMIN
    @PostMapping("/verify-admin")
    public ResponseEntity<?> verifyAdmin(@RequestBody Map<String, String> request) {

        String inputSecret = request.get("secret");
        String actualSecret = System.getProperty("ADMIN_SECRET");

        // Validate input
        if (inputSecret == null || inputSecret.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Secret is required");
        }

        // Extra safety: ensure env loaded
        if (actualSecret == null || actualSecret.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Admin secret not configured");
        }

        // Trim BOTH sides before comparing
        if (inputSecret.trim().equals(actualSecret.trim())) {
            return ResponseEntity.ok("VALID");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        try {
            request.getSession().invalidate(); // 🔥 destroys JSESSIONID
            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Logout failed");
        }
    }
}