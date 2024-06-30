package com.aladin.todolist.dto;

import com.aladin.todolist.model.Jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtDTO {
    
    private String id; 

    
    private boolean isDisable;
    private boolean isExpire;

    private String token;

    private UserDTO user;

    private RefreshTokenDTO refreshToken;



    public static JwtDTO fromEntity(Jwt jwt) {
        if (jwt == null) {
            return null;
        }

        return JwtDTO.builder()
                .id(jwt.getId())
                .isDisable(jwt.isDisable())
                .isExpire(jwt.isExpire())
                .token(jwt.getToken())
                .user(UserDTO.fromEntity(jwt.getUser()))
                .refreshToken(RefreshTokenDTO.fromEntity(jwt.getRefreshToken()))
                .build();
    }

    public static Jwt toEntity(JwtDTO jwtDTO) {
        if (jwtDTO == null) {
            return null;
        }

        return Jwt.builder()
                .id(jwtDTO.getId())
                .isDisable(jwtDTO.isDisable())
                .isExpire(jwtDTO.isExpire())
                .token(jwtDTO.getToken())
                .user(UserDTO.toEntity(jwtDTO.getUser()))
                .refreshToken(RefreshTokenDTO.toEntity(jwtDTO.getRefreshToken()))
                .build();
    }

}
