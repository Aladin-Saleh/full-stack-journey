package com.aladin.todolist.dto;
import com.aladin.todolist.model.Role;
import com.aladin.todolist.model.TypeRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    

    private String id;

    private TypeRole role;

    public static Role toEntity(RoleDTO dto) {

        if (dto == null) {
            return null;
        }
    
        Role role = new Role();
        role.setRole(dto.getRole());
        
        return role;

    }


    public static RoleDTO fromEntity(Role entity) {

        if (entity == null) {
            return null;
        }

        RoleDTO role = RoleDTO.builder()
        .role(entity.getRole())
        .build();

        return role;

    }



}
