package com.aladin.todolist.controller.api;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.todolist.dto.AuthentificationDTO;
import com.aladin.todolist.dto.UserDTO;


public interface UserAPI {
    

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> register(@RequestBody UserDTO userDTO);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> connection(@RequestBody AuthentificationDTO user);

    @PostMapping(value = "/disconnect")
    void logout();

    @PostMapping(value = "/refresh")
    Map<String, String> refresh(@RequestBody Map<String, String> refreshToken);


}
