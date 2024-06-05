package com.aladin.quizzapp.controllers.api;

import java.util.List;

import com.aladin.quizzapp.dto.LoginDTO;
import com.aladin.quizzapp.dto.RegisterDTO;
import com.aladin.quizzapp.dto.UserDTO;



public interface UserAPI {

    UserDTO register(RegisterDTO userDTO);

    UserDTO login(LoginDTO userDTO);

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

    UserDTO findById(Integer id);


    void delete(Integer id);

    List<UserDTO> findAll();

}
