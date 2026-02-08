package com.assistx.repository;

import com.assistx.model.User;
import com.assistx.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndRole(String email, Role role);
}
