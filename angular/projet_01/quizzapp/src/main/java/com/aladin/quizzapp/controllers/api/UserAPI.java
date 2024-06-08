package com.aladin.quizzapp.controllers.api;


import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.quizzapp.dto.AuthentificationDTO;
import com.aladin.quizzapp.dto.UserDTO;


public interface UserAPI {

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    void register(@RequestBody UserDTO userDTO);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> connection(@RequestBody AuthentificationDTO user);


}
