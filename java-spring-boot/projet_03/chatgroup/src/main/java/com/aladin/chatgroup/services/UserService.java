package com.aladin.chatgroup.services;


import org.springframework.stereotype.Service;

import com.aladin.chatgroup.models.User;
import com.aladin.chatgroup.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;


    public User createUser(User user) {
        // TODO: Ajouter des validations (validator)
        return this.repository.save(user);
    }

    public User findByUsername(String username) {
        return this.repository.findByUsername(username);
    }



}
