package com.aladin.chatgroup.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aladin.chatgroup.models.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    
}
