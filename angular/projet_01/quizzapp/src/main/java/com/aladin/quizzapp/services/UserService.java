package com.aladin.quizzapp.services;

import com.aladin.quizzapp.dto.LoginDTO;
import com.aladin.quizzapp.dto.RegisterDTO;
import com.aladin.quizzapp.dto.UserDTO;
import java.util.List;

public interface UserService {

    UserDTO register(RegisterDTO userDTO);

    UserDTO login(LoginDTO userDTO);

    List<UserDTO> findByUsername(String username);

    UserDTO findByEmail(String email);

    UserDTO findById(Integer id);


    void delete(Integer id);

    List<UserDTO> findAll();
    
}
