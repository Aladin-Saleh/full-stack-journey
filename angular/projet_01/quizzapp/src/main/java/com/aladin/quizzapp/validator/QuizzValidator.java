package com.aladin.quizzapp.validator;

import com.aladin.quizzapp.dto.QuizzDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class QuizzValidator {




    public static List<String> validate(QuizzDTO quizz) {

        List<String> errors = new ArrayList<>();

        if (quizz == null) {
            errors.add("Title field is empty !");
            errors.add("No teacher provided !");
            errors.add("The field id is not provided or not valid !");
            errors.add("No questions provided !");

        } else {

            if (!StringUtils.hasLength(quizz.getTitle())) {
                errors.add("Title field is empty !");
            }

            if (quizz.getTeacherDTO() == null) {
                errors.add("No teacher provided !");

            } else {
                if (quizz.getTeacherDTO().getId() == null) {
                    errors.add("The field id is not provided or not valid !");
                }
            }

            if (quizz.getQuestions() == null) {
                errors.add("No questions provided !");
            }
        }


        return errors;

    }


    
}
