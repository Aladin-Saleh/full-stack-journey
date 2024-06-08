package com.aladin.quizzapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.aladin.quizzapp.models.TeacherEntity;
import com.aladin.quizzapp.models.UserEntity;

import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
// @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeacherDTO extends UserDTO {




    private List<QuizzDTO> quizzs;



    
    public static TeacherDTO fromEntity(UserEntity entity) {
        if (entity == null) {
            // Throw exception
            return null;
        }


        TeacherEntity teacherEntity = (TeacherEntity) entity;
        TeacherDTO dto = new TeacherDTO();

        dto.setId(teacherEntity.getId());
        dto.setUsername(teacherEntity.getUsername());
        dto.setEmail(teacherEntity.getEmail());
        dto.setPassword(teacherEntity.getPassword());
        dto.setQuizzs(teacherEntity.getQuizzs() != null ? teacherEntity.getQuizzs().stream().map(QuizzDTO::fromEntity).collect(Collectors.toList()) : null);


        return dto;


    }

    public static TeacherEntity toEntity(TeacherDTO dto) {
        if (dto == null) {
            // Throw exception
            return null;
        }

        TeacherEntity entity = new TeacherEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setQuizzs(dto.getQuizzs() != null ? dto.getQuizzs().stream().map(QuizzDTO::toEntity).collect(Collectors.toList()) : null);

        return entity;
    }






}
