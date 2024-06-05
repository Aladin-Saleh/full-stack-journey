package com.aladin.quizzapp.repository;

import com.aladin.quizzapp.models.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    UserEntity findByEmail(String email);

    List<UserEntity> findByUsername(String username);


}
