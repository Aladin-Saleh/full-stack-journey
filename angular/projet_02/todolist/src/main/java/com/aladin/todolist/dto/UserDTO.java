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

    private ToDoListDTO toDoList;


    
    public static User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }
    
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(RoleDTO.toEntity(dto.getRole()));
        user.setToDoList(ToDoListDTO.toEntity(dto.getToDoList()));
        user.setUsername(dto.getUsername());
        
        
        return user;
    }


    public static UserDTO fromEntity(User entity) {

        if (entity == null) {
            return null;
        }

        UserDTO user = UserDTO.builder()
        .email(entity.getEmail())
        .username(entity.getUsername())
        .password(entity.getPassword())
        .role(RoleDTO.builder().role(entity.getRole().getRole()).build())
        .toDoList(ToDoListDTO.fromEntity(entity.getToDoList()))
        .build();

        return user;

    }


    


}
