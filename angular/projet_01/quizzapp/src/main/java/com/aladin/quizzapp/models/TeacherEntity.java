package com.aladin.quizzapp.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
public class TeacherEntity extends UserEntity {


    @OneToMany(mappedBy = "teacher")
    private List<QuizzEntity> quizzs;


    
}
