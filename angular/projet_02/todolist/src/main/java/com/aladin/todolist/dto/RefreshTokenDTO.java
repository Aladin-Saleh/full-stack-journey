package com.aladin.todolist.dto;

import java.util.Date;

import com.aladin.todolist.model.RefreshToken;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenDTO {

    private String id;

    private String token;

    private Date expirationDate;
    
    private boolean isExpired;


    public static RefreshToken toEntity(RefreshTokenDTO refreshToken) {

        if (refreshToken == null) {
            return null;
        }

        return RefreshToken.builder()
            .id(refreshToken.getId())
            .token(refreshToken.getToken())
            .expirationDate(refreshToken.getExpirationDate())
            .isExpired(refreshToken.isExpired())
            .build();
    }


    public static RefreshTokenDTO fromEntity(RefreshToken refreshToken) {

        if (refreshToken == null) {
            return null;
        }

        return RefreshTokenDTO.builder()
            .id(refreshToken.getId())
            .token(refreshToken.getToken())
            .expirationDate(refreshToken.getExpirationDate())
            .isExpired(refreshToken.isExpired())
            .build();

        
    }


    
}
