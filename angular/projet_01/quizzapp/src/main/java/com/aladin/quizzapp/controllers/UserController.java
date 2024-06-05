package com.aladin.quizzapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.aladin.quizzapp.controllers.api.UserAPI;
import com.aladin.quizzapp.dto.LoginDTO;
import com.aladin.quizzapp.dto.RegisterDTO;
import com.aladin.quizzapp.dto.UserDTO;
import com.aladin.quizzapp.services.UserService;



@RestController
public class UserController implements UserAPI {


    private UserService userService;

    @Override
    public UserDTO register(RegisterDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public UserDTO login(LoginDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public UserDTO findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public UserDTO findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public UserDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }


    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<UserDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    
}
