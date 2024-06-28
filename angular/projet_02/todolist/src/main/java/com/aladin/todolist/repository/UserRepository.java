package com.aladin.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.todolist.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    Optional<User> findByUsername(String username);
    
}
