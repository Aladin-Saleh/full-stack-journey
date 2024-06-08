package com.aladin.quizzapp.services.implementation;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.RoleDTO;
import com.aladin.quizzapp.dto.UserDTO;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.exception.InvalidEntityException;
import com.aladin.quizzapp.models.TypeRole;
import com.aladin.quizzapp.models.UserEntity;
import com.aladin.quizzapp.repository.UserRepository;
import com.aladin.quizzapp.services.UserService;
import com.aladin.quizzapp.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDTO user) {
        RoleDTO role = new RoleDTO();
        List<String> errors = UserValidator.validate(user);

        if (user == null) {
            log.error("No user provided !");
            return;
        }

        if (!errors.isEmpty()) {
            log.error("Invalid user provided !", errors);
            throw new InvalidEntityException("Invalid user provided !", ErrorCodes.USER_NOT_VALID, errors);
        }

        role.setLibelle(TypeRole.STUDENT);
        user.setRole(role);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(UserDTO.toEntity(user));
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //     return this.userRepository.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("Username not valid/don't exist !"));

    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not valid/don't exist!"));

        new User(userEntity.getUsername(), userEntity.getPassword(), List.of(new SimpleGrantedAuthority(userEntity.getRole().getLibelle().name())));

        return userEntity;
    }

}
