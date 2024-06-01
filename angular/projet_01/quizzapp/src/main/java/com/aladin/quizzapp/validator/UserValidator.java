package com.aladin.quizzapp.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.aladin.quizzapp.dto.UserDTO;

public class UserValidator {
    



    public static List<String> validate(UserDTO user) {

        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add("The field email is empty !");
            errors.add("The field username is empty !");
            errors.add("The field password is empty !");

        } else {

            if (!StringUtils.hasLength(user.getMail())) {
                errors.add("The field email is empty !");
            }

            
            if (!StringUtils.hasLength(user.getUsername())) {
                errors.add("The field username is empty !");
            }

            
            if (!StringUtils.hasLength(user.getPassword())) {
                errors.add("The field password is empty !");
            }

        }


        return errors;

    }

}
