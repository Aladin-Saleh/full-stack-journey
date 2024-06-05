package com.aladin.quizzapp.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.aladin.quizzapp.dto.RegisterDTO;


public class RegisterValidator {

        public static List<String> validate(RegisterDTO user) {

        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add("The field email is empty !");
            errors.add("The field username is empty !");
            errors.add("The field password is empty !");
            errors.add("The password is too short !");


        } else {

            if (!StringUtils.hasLength(user.getEmail())) {
                errors.add("The field email is empty !");
            }

            
            if (!StringUtils.hasLength(user.getUsername())) {
                errors.add("The field username is empty !");
            } else {
                if (user.getUsername().length() >= 16 ) {
                    errors.add("The username is too long !");
                }

                if (user.getUsername().length() <= 3) {
                    errors.add("The username is too short !");
                }
            }

            
            if (!StringUtils.hasLength(user.getPassword())) {
                errors.add("The field password is empty !");
            } else {
                if (user.getPassword().length() <= 5 ) {
                    errors.add("The password is too short !");
                }

            }

            if (user.getIsTeacher() == null) {
                errors.add("The field isTeacher not provided !");
            }

        }

        return errors;

    }
    
}
