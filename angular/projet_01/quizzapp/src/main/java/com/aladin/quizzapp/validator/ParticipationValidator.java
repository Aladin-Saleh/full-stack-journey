package com.aladin.quizzapp.validator;

import java.util.ArrayList;
import java.util.List;

import com.aladin.quizzapp.dto.ParticipationDTO;

public class ParticipationValidator {

    public static List<String> validate(ParticipationDTO participation) {

        List<String> errors = new ArrayList<>();

        if (participation == null) {
            errors.add("No student provided !");
            errors.add("No studentId provided for the student !");
            errors.add("No quizz provided !");
            errors.add("No quizzId provided for the quizz !");

        } else {

            if (participation.getStudent() == null) {
                errors.add("No student provided !");

            } else {

                if (participation.getStudent().getId() == null) {
                    errors.add("No studentId provided for the student !");
                }
            }

            if (participation.getQuizz() == null) {
                errors.add("No quizz provided !");

            } else {

                if (participation.getQuizz().getId() == null) {
                    errors.add("No quizzId provided for the quizz !");

                }

            }

        }

        return errors;
    }

}
