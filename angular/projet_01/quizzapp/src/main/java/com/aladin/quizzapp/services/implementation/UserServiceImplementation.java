package com.aladin.quizzapp.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aladin.quizzapp.dto.LoginDTO;
import com.aladin.quizzapp.dto.RegisterDTO;
import com.aladin.quizzapp.dto.UserDTO;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.exception.InvalidEntityException;
import com.aladin.quizzapp.models.UserEntity;
import com.aladin.quizzapp.repository.UserRepository;
import com.aladin.quizzapp.services.UserService;
import com.aladin.quizzapp.validator.RegisterValidator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    // private PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        List<String> errors = RegisterValidator.validate(registerDTO);


        if (!errors.isEmpty()) {
            log.error("The register's informations provided are not valid !");
            throw new InvalidEntityException("The register's informations provided are not valid !", ErrorCodes.USER_NOT_VALID, errors);
        }


        if (this.isEmailExist(registerDTO.getEmail())) {
            log.error("The email provided is already used !");
            throw new InvalidEntityException("The email provided is already used !", ErrorCodes.USER_NOT_VALID);
        }

        return null;

    }


    public UserEntity createStudent(RegisterDTO register) {
        // TODO : creation du student !
        return null;
    }

    public UserEntity createTeacher(RegisterDTO register) {
        // TODO : creation du teacher !
        return null;
    }

    @Override
    public UserDTO login(LoginDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public List<UserDTO> findByUsername(String username) {

        if (username == null) {
            log.error("No username provided !");
            return null;
        }

        List<UserDTO> user = this.userRepository.findByUsername(username).stream().map(UserDTO::fromEntity)
                .collect(Collectors.toList());
        return user;

    }

    @Override
    public UserDTO findByEmail(String email) {

        if (email == null) {
            log.error("No email provided !");
            return null;
        }

        UserDTO user = UserDTO.fromEntity(this.userRepository.findByEmail(email));
        return user;
    }

    @Override
    public UserDTO findById(Integer id) {

        if (id == null) {
            log.error("No id provided !");
            return null;
        }

        UserDTO user = UserDTO.fromEntity(this.userRepository.findById(id).orElse(null));
        return user;
    }

    @Override
    public void delete(Integer id) {

        UserDTO isUserExist = UserDTO.fromEntity(this.userRepository.findById(id).orElse(null));
        if (isUserExist == null) {
            log.error("User id not valid !");
            throw new InvalidEntityException("User id not valid !", ErrorCodes.USER_NOT_VALID);
        }

        this.userRepository.deleteById(id);
    }


    @Override
    public List<UserDTO> findAll() {
        return this.userRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }

    public Boolean isEmailExist(String email) {

        return this.userRepository.findByEmail(email) != null;
    }

}
