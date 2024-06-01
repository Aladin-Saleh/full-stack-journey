package com.aladin.quizzapp.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.aladin.quizzapp.dto.QuestionDTO;

public class QuestionValidator {

    public static List<String> validate(QuestionDTO question) {
        List<String> errors = new ArrayList<>();

        if (question == null) {
            errors.add("No title provided !");
            errors.add("No quizz provided !");
            errors.add("No quizzId provided !");
            errors.add("No response provided for the question !");
            errors.add("No responseId provided !");
            errors.add("No choices provided for the question !");
            errors.add("There is not choice to the question provided !");

        } else {

            if (!StringUtils.hasLength(question.getTitle())) {
                errors.add("No title provided !");
            }

            if (question.getQuizz() == null) {
                errors.add("No quizz provided !");

            } else {

                if (question.getQuizz().getId() == null) {
                    errors.add("No quizzId provided !");
                }
            }

            if (question.getResponse() == null) {
                errors.add("No response provided for the question !");

            } else {

                if (question.getResponse().getId() == null) {
                    errors.add("No responseId provided !");
                }
            }

            if (question.getChoices() == null) {
                errors.add("No choices provided for the question !");

            } else {

                if (question.getChoices().size() == 0) {
                    errors.add("There is not choice to the question provided !");
                }
            }
        }

        return errors;
    }

}
