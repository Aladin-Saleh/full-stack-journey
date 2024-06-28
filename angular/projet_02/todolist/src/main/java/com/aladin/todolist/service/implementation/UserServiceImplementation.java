package com.aladin.todolist.service.implementation;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aladin.todolist.dto.RoleDTO;
import com.aladin.todolist.dto.UserDTO;
import com.aladin.todolist.exception.InvalidEntityException;
import com.aladin.todolist.model.TypeRole;
import com.aladin.todolist.model.User;
import com.aladin.todolist.repository.UserRepository;
import com.aladin.todolist.service.UserService;
import com.aladin.todolist.validator.UserValidator;

import com.aladin.todolist.exception.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class  UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(UserDTO user) {
        RoleDTO role = new RoleDTO();
        List<String> errors = UserValidator.validate(user);

        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Invalid user provided !", ErrorCodes.USER_NOT_VALID, errors);
        }

        if (isEmailAlreadyUse(user.getEmail()))
        {
            throw new InvalidEntityException("Invalid user provided : Email is already used !", ErrorCodes.USER_NOT_VALID);
        }

        role.setRole(TypeRole.USER);
        user.setRole(role);
        log.info("User {} was registered", user.getEmail());

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        

        this.repository.save(UserDTO.toEntity(user));


    }


    public boolean isEmailAlreadyUse(String email)
    {
        UserDTO user = UserDTO.fromEntity(this.repository.findByEmail(email));

        return user != null; 
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not valid/don't exist!"));

        new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().getRole().name())));

        return user;
    }
}
