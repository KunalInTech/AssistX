package com.assistx.service;

import com.assistx.entity.UserEntity;
import com.assistx.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * WHITE BOX TESTING - UserService
 * Tests internal logic, code paths, and implementation details
*/
class UserServiceWhiteBoxTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testUser = new UserEntity();
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
    }

    // ========================================
    // WB-TC-01: Test Password Encryption Path
    // ========================================
    @Test
    @DisplayName("WB-TC-01: Password should be encrypted using BCrypt")
    void testPasswordEncryptionPath() {
        // Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("$2a$12$encrypted");
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUser);

        // Act
        userService.registerUser(testUser);

        // Assert - Verify BCrypt encode was called
        verify(passwordEncoder, times(1)).encode("password123");
        
        // Verify password was set to encrypted value
        assertEquals("$2a$12$encrypted", testUser.getPassword());
    }

    // ========================================
    // WB-TC-02: Test Username Uniqueness Check Path
    // ========================================
    @Test
    @DisplayName("WB-TC-02: Should check username uniqueness before registration")
    void testUsernameUniquenessCheckPath() {
        // Arrange
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        // Act & Assert
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(testUser);
        });

        // Verify uniqueness check was called
        verify(userRepository, times(1)).existsByUsername("testuser");
        
        // Verify save was NOT called (early exit)
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    // ========================================
    // WB-TC-03: Test Email Uniqueness Check Path
    // ========================================
    @Test
    @DisplayName("WB-TC-03: Should check email uniqueness before registration")
    void testEmailUniquenessCheckPath() {
        // Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        // Act & Assert
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(testUser);
        });

        // Verify email check was called
        verify(userRepository, times(1)).existsByEmail("test@example.com");
    }

    // ========================================
    // WB-TC-04: Test Validation Method Calls
    // ========================================
    @Test
    @DisplayName("WB-TC-04: Should call all validation methods in correct order")
    void testValidationMethodCallOrder() {
        // Arrange
        testUser.setUsername("ab"); // Too short

        // Act & Assert
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(testUser);
        });

        // Verify validation happened before database checks
        verify(userRepository, never()).existsByUsername(anyString());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    // ========================================
    // WB-TC-05: Test Authentication Logic Path
    // ========================================
    @Test
    @DisplayName("WB-TC-05: Authentication should verify password using BCrypt")
    void testAuthenticationPasswordVerificationPath() {
        // Arrange
        UserEntity storedUser = new UserEntity();
        storedUser.setUsername("testuser");
        storedUser.setPassword("$2a$12$encryptedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("password123", "$2a$12$encryptedPassword")).thenReturn(true);

        // Act
        Optional<UserEntity> result = userService.authenticateUser("testuser", "password123");

        // Assert
        assertTrue(result.isPresent());
        
        // Verify password matching was called
        verify(passwordEncoder, times(1)).matches("password123", "$2a$12$encryptedPassword");
    }

    // ========================================
    // WB-TC-06: Test Failed Authentication Path
    // ========================================
    @Test
    @DisplayName("WB-TC-06: Failed authentication should not proceed further")
    void testFailedAuthenticationPath() {
        // Arrange
        UserEntity storedUser = new UserEntity();
        storedUser.setPassword("$2a$12$encrypted");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("wrongpassword", "$2a$12$encrypted")).thenReturn(false);

        // Act
        Optional<UserEntity> result = userService.authenticateUser("testuser", "wrongpassword");

        // Assert
        assertFalse(result.isPresent());
        
        // Verify password matching was attempted
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    // ========================================
    // WB-TC-07: Test Branch Coverage - Null Username
    // ========================================
    @Test
    @DisplayName("WB-TC-07: Should handle null username in validation")
    void testNullUsernameBranch() {
        // Arrange
        testUser.setUsername(null);

        // Act & Assert
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(testUser);
        });
    }

    // ========================================
    // WB-TC-08: Test Loop Coverage - Reserved Username Check
    // ========================================
    @Test
    @DisplayName("WB-TC-08: Should reject reserved usernames")
    void testReservedUsernameLoop() {
        // Arrange
        testUser.setUsername("admin"); // Reserved username

        // Act & Assert
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(testUser);
        });
    }

    // ========================================
    // WB-TC-09: Test Method Call Verification
    // ========================================
    @Test
    @DisplayName("WB-TC-09: Successful registration should call save exactly once")
    void testSaveMethodCallCount() {
        // Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$12$encrypted");
        when(userRepository.save(any(UserEntity.class))).thenReturn(testUser);

        // Act
        userService.registerUser(testUser);

        // Assert
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    // ========================================
    // WB-TC-10: Test Exception Handling Path
    // ========================================
    @Test
    @DisplayName("WB-TC-10: Should propagate repository exceptions")
    void testRepositoryExceptionHandling() {
        // Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$12$encrypted");
        when(userRepository.save(any(UserEntity.class)))
            .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userService.registerUser(testUser);
        });
    }
}