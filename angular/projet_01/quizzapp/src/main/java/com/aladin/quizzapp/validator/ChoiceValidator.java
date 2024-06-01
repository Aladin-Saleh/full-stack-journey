package com.aladin.quizzapp.validator;

import com.aladin.quizzapp.dto.ChoiceDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;


public class ChoiceValidator {




    public static List<String> validate(ChoiceDTO choice) {

        List<String> errors = new ArrayList<>();

        if (choice == null) {
            errors.add("Choice content is empty !");
            errors.add("Question provided is not valid or don't exist !");
            errors.add("Title field is empty ! (for question)");

        } else {


            if (!StringUtils.hasLength(choice.getContent())) {
                errors.add("Choice content is empty !");
            }
    
            if (choice.getQuestion() == null) {
                errors.add("Question provided is not valid or don't exist !");
            } else {
    
                if (!StringUtils.hasLength(choice.getQuestion().getTitle())) {
                    errors.add("Title field is empty ! (for question)");
                }

                if (choice.getQuestion().getChoices() == null) {
                    errors.add("Choice is empty !");
                }
            }

        }

        return errors;
    }




    
}
