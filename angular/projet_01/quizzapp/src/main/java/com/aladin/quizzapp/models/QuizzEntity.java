package com.aladin.quizzapp.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Quizz")
public class QuizzEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "quizz")
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "quizz")
    private List<ParticipationEntity> participations;
    


}
