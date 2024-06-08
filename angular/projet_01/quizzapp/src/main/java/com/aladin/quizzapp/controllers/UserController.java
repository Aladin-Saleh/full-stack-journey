package com.aladin.quizzapp.controllers;


import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.quizzapp.config.JWTService;
import com.aladin.quizzapp.controllers.api.UserAPI;
import com.aladin.quizzapp.dto.AuthentificationDTO;
import com.aladin.quizzapp.dto.UserDTO;
import com.aladin.quizzapp.services.implementation.UserServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController implements UserAPI {

    private UserServiceImplementation userService;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    @Override
    public void register(UserDTO userDTO) {

        log.info("inscription");
        this.userService.register(userDTO);
    }

    @Override
    public Map<String, String> connection(AuthentificationDTO user) {

        final Authentication auth = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.username(), user.password())
        );
        log.info("resultat {}", auth.isAuthenticated());
        if (auth.isAuthenticated()) {
            return this.jwtService.generate(user.username());
        }

        return null;
    }

}
