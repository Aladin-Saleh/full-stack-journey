package com.aladin.quizzapp.dto;

import com.aladin.quizzapp.models.ParticipationEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParticipationDTO {

    private Integer id;

    private StudentDTO student;

    private QuizzDTO quizz;

    private Integer score;

    public static ParticipationDTO fromEntity(ParticipationEntity entity) {

        if (entity == null) {
            // Throw exception
            return null;
        }

        return ParticipationDTO.builder()
                .id(entity.getId())
                .student(StudentDTO.fromEntity(entity.getStudent()))
                .quizz(QuizzDTO.fromEntity(entity.getQuizz()))
                .score(entity.getScore())
                .build();
    }


    public static ParticipationEntity toEntity(ParticipationDTO dto) {
        
        if (dto == null) {
            // Throw exception
            return null;
        }

        ParticipationEntity entity = new ParticipationEntity();
        entity.setId(dto.getId());
        entity.setStudent(StudentDTO.toEntity(dto.getStudent()));
        entity.setQuizz(QuizzDTO.toEntity(dto.getQuizz()));
        entity.setScore(dto.getScore());

        return entity;

    }

}
