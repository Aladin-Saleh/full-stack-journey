package com.aladin.todolist.dto;


import com.aladin.todolist.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    

    private String id;

    private String username;

    private String email;

    private String password;

    private RoleDTO role;

    private String toDoListId;



    
    public static User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }
    
        User user = User.builder()
        .id(dto.getId())
        .email(dto.getEmail())
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(RoleDTO.toEntity(dto.getRole()))
        .toDoListId(dto.getToDoListId())
        .build();
        
        
        return user;
    }


    public static UserDTO fromEntity(User entity) {

        if (entity == null) {
            return null;
        }

        UserDTO user = UserDTO.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .username(entity.getUsername())
        .password(entity.getPassword())
        .role(RoleDTO.builder().role(entity.getRole().getRole()).build())
        .toDoListId(entity.getToDoListId())
        .build();

        return user;

    }


    


}
