package com.aladin.todolist.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.todolist.config.JWTService;
import com.aladin.todolist.controller.api.UserAPI;
import com.aladin.todolist.dto.AuthentificationDTO;
import com.aladin.todolist.dto.UserDTO;
import com.aladin.todolist.exception.InvalidEntityException;
import com.aladin.todolist.service.implementation.UserServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController implements UserAPI {

    private final UserServiceImplementation userService;
    private final AuthenticationManager authenticationManager;
    private JWTService jwtService;

    
    @Override
    public ResponseEntity<String> register(UserDTO userDTO) {

        try {

            log.info("Registering user {}", userDTO.getEmail());
            this.userService.register(userDTO);
            return ResponseEntity.ok().body("User registered successfully");

        } catch (InvalidEntityException exception) {

            log.error("Error during registration of user {}", userDTO.getEmail(), exception);
            return ResponseEntity.badRequest().body(exception.getMessage());

        } catch (Exception exception) {

            log.error("Error during registration of user {}", userDTO.getEmail(), exception);
            return ResponseEntity.status(500).body(exception.getMessage());

        }
    }

    @Override
    public Map<String, String> connection(AuthentificationDTO authDTO) {

        final Authentication auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password()));

        log.info("resultat {}", auth.isAuthenticated());
        if (auth.isAuthenticated()) {
            return this.jwtService.generate(authDTO.username());
        }

        return null;
    }

    @Override
    public void logout() {
        this.jwtService.logout();
    }

    @Override
    public Map<String, String> refresh(Map<String, String> refreshToken) {

        return this.jwtService.refreshToken(refreshToken);
    }

    

}
