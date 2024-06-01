package com.aladin.quizzapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.aladin.quizzapp.models.QuestionEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionDTO {

    private Integer id;

    private String title;

    private List<ChoiceDTO> choices;

    private QuizzDTO quizz;

    private ChoiceDTO response;


    public static QuestionDTO fromEntity(QuestionEntity entity) {

        if (entity == null) {
            // Throw exception
            return null;
        }

        return QuestionDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .choices(entity.getChoices().stream().map(ChoiceDTO::fromEntity).collect(Collectors.toList()))
                .quizz(QuizzDTO.fromEntity(entity.getQuizz()))
                .response(ChoiceDTO.fromEntity(entity.getResponse()))
                .build();
    }


    public static QuestionEntity toEntity(QuestionDTO question) {
            
            if (question == null) {
                // Throw exception
                return null;
            }
    
            QuestionEntity entity = new QuestionEntity();
            entity.setId(question.getId());
            entity.setTitle(question.getTitle());
            entity.setChoices(question.getChoices().stream().map(ChoiceDTO::toEntity).collect(Collectors.toList()));
            entity.setQuizz(QuizzDTO.toEntity(question.getQuizz()));
            entity.setResponse(ChoiceDTO.toEntity(question.getResponse()));
    
            return entity;

    }
}
