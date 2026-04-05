package com.assistx.controller;

import com.assistx.entity.UserEntity;
import com.assistx.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BLACK BOX TESTING - User Registration
 * Tests functionality from user perspective without knowing internal code
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRegistrationBlackBoxTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    // ========================================
    // BB-TC-01: Valid User Registration
    // ========================================
    @Test
    @Order(1)
    @DisplayName("BB-TC-01: Valid registration should return success")
    void testValidRegistration() {
        // Input
        UserEntity user = new UserEntity();
        user.setUsername("blackbox_user1");
        user.setEmail("bb1@example.com");
        user.setPassword("password123");
        user.setPhoneNumber("1234567890");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("success"));
    }

    // ========================================
    // BB-TC-02: Duplicate Username
    // ========================================
    @Test
    @Order(2)
    @DisplayName("BB-TC-02: Duplicate username should be rejected")
    void testDuplicateUsername() {
        // Input - Same username as TC-01
        UserEntity user = new UserEntity();
        user.setUsername("blackbox_user1");
        user.setEmail("different@example.com");
        user.setPassword("password123");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Username already exists") ||
                   response.getBody().contains("already taken"));
    }

    // ========================================
    // BB-TC-03: Invalid Email Format
    // ========================================
    @Test
    @Order(3)
    @DisplayName("BB-TC-03: Invalid email should be rejected")
    void testInvalidEmailFormat() {
        // Input - Invalid email
        UserEntity user = new UserEntity();
        user.setUsername("blackbox_user2");
        user.setEmail("invalid-email");
        user.setPassword("password123");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("email") ||
                   response.getBody().contains("Invalid"));
    }

    // ========================================
    // BB-TC-04: Short Password
    // ========================================
    @Test
    @Order(4)
    @DisplayName("BB-TC-04: Short password should be rejected")
    void testShortPassword() {
        // Input - Password < 6 characters
        UserEntity user = new UserEntity();
        user.setUsername("blackbox_user3");
        user.setEmail("bb3@example.com");
        user.setPassword("12345"); // Only 5 characters

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("password") ||
                   response.getBody().contains("6 characters"));
    }

    // ========================================
    // BB-TC-05: Missing Required Field
    // ========================================
    @Test
    @Order(5)
    @DisplayName("BB-TC-05: Missing email should be rejected")
    void testMissingRequiredField() {
        // Input - No email
        UserEntity user = new UserEntity();
        user.setUsername("blackbox_user4");
        user.setPassword("password123");
        // email is null

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // ========================================
    // BB-TC-06: Valid Login
    // ========================================
    @Test
    @Order(6)
    @DisplayName("BB-TC-06: Valid login should succeed")
    void testValidLogin() {
        // Input - Credentials from TC-01
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "blackbox_user1");
        credentials.put("password", "password123");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/login",
            credentials,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("success"));
    }

    // ========================================
    // BB-TC-07: Invalid Login Credentials
    // ========================================
    @Test
    @Order(7)
    @DisplayName("BB-TC-07: Wrong password should fail")
    void testInvalidLogin() {
        // Input - Wrong password
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "blackbox_user1");
        credentials.put("password", "wrongpassword");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/login",
            credentials,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    // ========================================
    // BB-TC-08: Login Non-Existent User
    // ========================================
    @Test
    @Order(8)
    @DisplayName("BB-TC-08: Non-existent user login should fail")
    void testNonExistentUserLogin() {
        // Input - User that doesn't exist
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "nonexistent");
        credentials.put("password", "password123");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/login",
            credentials,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    // ========================================
    // BB-TC-09: Special Characters in Username
    // ========================================
    @Test
    @Order(9)
    @DisplayName("BB-TC-09: Special characters in username should be handled")
    void testSpecialCharactersInUsername() {
        // Input - Username with special characters
        UserEntity user = new UserEntity();
        user.setUsername("user@#$%");
        user.setEmail("bb9@example.com");
        user.setPassword("password123");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Invalid") ||
                   response.getBody().contains("username"));
    }

    // ========================================
    // BB-TC-10: Boundary Value - Username Length
    // ========================================
    @Test
    @Order(10)
    @DisplayName("BB-TC-10: Username at boundary values should be validated")
    void testUsernameBoundaryValues() {
        // Input - Username with exactly 3 characters (minimum)
        UserEntity user = new UserEntity();
        user.setUsername("abc");
        user.setEmail("bb10@example.com");
        user.setPassword("password123");

        // Action
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/auth/register",
            user,
            String.class
        );

        // Expected Output - Should accept minimum valid length
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}