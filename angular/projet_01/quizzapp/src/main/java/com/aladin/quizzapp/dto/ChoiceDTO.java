package com.aladin.quizzapp.dto;

import com.aladin.quizzapp.models.ChoiceEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChoiceDTO {

    private Integer id;

    private QuestionDTO question;

    private QuestionDTO responseForQuestion;
    

    private String content;


    public static ChoiceDTO fromEntity(ChoiceEntity entity) {

        if (entity == null) {
            // Throw exception
            return null;
        }

        return ChoiceDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .question(QuestionDTO.fromEntity(entity.getQuestion()))
                .responseForQuestion(QuestionDTO.fromEntity(entity.getResponseForQuestion()))
                .build();
    }


    public static ChoiceEntity toEntity(ChoiceDTO dto) {

        if (dto == null) {
            // Throw exception
            return null;
        }


        ChoiceEntity entity = new ChoiceEntity();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setResponseForQuestion(QuestionDTO.toEntity(dto.getResponseForQuestion()));
        entity.setQuestion(QuestionDTO.toEntity(dto.getQuestion()));

        return entity;



    }

}
