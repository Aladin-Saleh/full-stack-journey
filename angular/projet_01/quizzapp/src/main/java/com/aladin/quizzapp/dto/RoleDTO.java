package com.aladin.quizzapp.dto;

import com.aladin.quizzapp.models.RoleEntity;
import com.aladin.quizzapp.models.TypeRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    
    private TypeRole libelle;



    public static RoleDTO fromEntity(RoleEntity role) {

        if (role == null) {
            return null;
        }

        RoleDTO dto = RoleDTO.builder()
                .libelle(role.getLibelle())
                .build();

        return dto;

    }


    public static RoleEntity toEntity(RoleDTO role) {
        if (role == null) {
            return null;
        }

        RoleEntity entity = new RoleEntity();
        entity.setLibelle(role.getLibelle());

        return entity;

    }
}
