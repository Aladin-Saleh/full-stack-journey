package com.aladin.quizzapp.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.aladin.quizzapp.dto.UserDTO;

public class UserValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static List<String> validate(UserDTO user) {

        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add("The field email is empty !");
            errors.add("The field username is empty !");
            errors.add("The field password is empty !");
            errors.add("The field email is not valid !");

        } else {

            if (!StringUtils.hasLength(user.getEmail())) {
                errors.add("The field email is empty !");
            } else {
                if (!isValidEmail(user.getEmail())) {
                    errors.add("The field email is not valid !");

                }
            }

            if (!StringUtils.hasLength(user.getUsername())) {
                errors.add("The field username is empty !");
            } else {
                if (user.getUsername().length() >= 16) {
                    errors.add("The username is too long !");
                }

                if (user.getUsername().length() <= 3) {
                    errors.add("The username is too short !");
                }
            }

            if (!StringUtils.hasLength(user.getPassword())) {
                errors.add("The field password is empty !");
            }

        }

        return errors;

    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
