package com.aladin.quizzapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.aladin.quizzapp.models.QuizzEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuizzDTO  {

    private Integer id;

    private String title;

    private TeacherDTO teacherDTO;

    private List<QuestionDTO> questions;

    private List<ParticipationDTO> participations;

    public static QuizzDTO fromEntity(QuizzEntity quizz) {

        if (quizz == null) {
            // Throw exception
            return null;
        }

        return QuizzDTO.builder()
                .id(quizz.getId())
                .title(quizz.getTitle())
                .teacherDTO(TeacherDTO.fromEntity(quizz.getTeacher()))
                .questions(quizz.getQuestions().stream().map(QuestionDTO::fromEntity).collect(Collectors.toList()))
                .participations(quizz.getParticipations().stream().map(ParticipationDTO::fromEntity).collect(Collectors.toList()))
                .build();

    }

    public static QuizzEntity toEntity(QuizzDTO quizz) {
            
            if (quizz == null) {
                // Throw exception
                return null;
            }
    
            QuizzEntity entity = new QuizzEntity();
            entity.setId(quizz.getId());
            entity.setTitle(quizz.getTitle());
            entity.setTeacher(TeacherDTO.toEntity(quizz.getTeacherDTO()));
            entity.setQuestions(quizz.getQuestions().stream().map(QuestionDTO::toEntity).collect(Collectors.toList()));
            entity.setParticipations(quizz.getParticipations().stream().map(ParticipationDTO::toEntity).collect(Collectors.toList()));
    
            return entity;

    }






}
