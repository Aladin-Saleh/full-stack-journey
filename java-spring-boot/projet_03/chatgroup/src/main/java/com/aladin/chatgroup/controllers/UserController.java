package com.aladin.chatgroup.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.chatgroup.models.User;
import com.aladin.chatgroup.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService service;


    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        this.service.createUser(user);
        log.info("User created " + user);
        return user;
    }


    @GetMapping("/find/{username}")
    public User findByUsername(@PathVariable String username) {
        return this.service.findByUsername(username);
    }

    
}
