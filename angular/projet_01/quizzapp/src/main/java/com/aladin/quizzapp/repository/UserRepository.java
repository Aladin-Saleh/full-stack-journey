package com.aladin.quizzapp.repository;

import com.aladin.quizzapp.dto.RegisterDTO;
import com.aladin.quizzapp.models.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    UserEntity findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    UserEntity save(RegisterDTO user);




}
