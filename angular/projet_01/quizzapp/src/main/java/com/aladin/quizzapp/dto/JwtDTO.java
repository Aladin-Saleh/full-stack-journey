package com.aladin.quizzapp.dto;


import com.aladin.quizzapp.models.JwtEntity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
public class JwtDTO {

    private Integer id;

    private boolean isDisable;

    private boolean isExpire;

    private String token;

    private UserDTO user;



    public static JwtDTO fromEntity(JwtEntity jwtEntity) {

        if (jwtEntity == null) {
            // Throw exception or handle error
            return null;
        }

        
        return JwtDTO.builder()
                .id(jwtEntity.getId())
                .isDisable(jwtEntity.isDisable())
                .isExpire(jwtEntity.isExpire())
                .token(jwtEntity.getToken())
                .user(UserDTO.fromEntity(jwtEntity.getUser()))
                .build();
    }

    public static JwtEntity toEntity(JwtDTO jwtDTO) {

        if (jwtDTO == null) {
            // Throw exception or handle error
            return null;
        }

        JwtEntity entity = new JwtEntity();
        entity.setId(jwtDTO.getId());
        entity.setDisable(jwtDTO.isDisable());
        entity.setExpire(jwtDTO.isExpire());
        entity.setToken(jwtDTO.getToken());
        log.info(jwtDTO.getUser().toString());
        entity.setUser(UserDTO.toEntity(jwtDTO.getUser()));
        
        return entity;

    }
    
}
