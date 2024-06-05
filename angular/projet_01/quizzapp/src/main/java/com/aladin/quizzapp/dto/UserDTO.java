package com.aladin.quizzapp.dto;


import com.aladin.quizzapp.models.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Integer id;

    private String username;

    private String email;

    private String password;

    public static UserDTO fromEntity(UserEntity user) {

        if (user == null) {
            return null;
        }

        UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return dto;

    }

    public static UserEntity toEntity(UserDTO user) {
        if (user == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        return entity;

    }

}
