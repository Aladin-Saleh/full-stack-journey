package com.aladin.quizzapp.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.aladin.quizzapp.models.StudentEntity;
import com.aladin.quizzapp.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class StudentDTO extends UserDTO {


    
    private List<ParticipationDTO> participations;


    public static StudentDTO fromEntity(UserEntity student) {
        if (student == null) {
            // Throw exception
            return null;
        }


        StudentEntity studentEntity = (StudentEntity) student;
        StudentDTO dto = new StudentDTO();

        dto.setId(studentEntity.getId());
        dto.setMail(studentEntity.getMail());
        dto.setPassword(studentEntity.getPassword());
        dto.setUsername(studentEntity.getUsername());
        dto.setParticipations(studentEntity.getParticipations().stream().map(ParticipationDTO::fromEntity).collect(Collectors.toList()));

        return dto;
    }

    public static StudentEntity toEntity(StudentDTO student) {

        if (student == null) {
            // Throw exception
            return null;
        }

        StudentEntity entity = new StudentEntity();
        entity.setId(student.getId());
        entity.setUsername(student.getUsername());
        entity.setMail(student.getMail());
        entity.setPassword(student.getPassword());
        entity.setParticipations(student.getParticipations().stream().map(ParticipationDTO::toEntity).collect(Collectors.toList()));

        return entity;

    }

    
}
