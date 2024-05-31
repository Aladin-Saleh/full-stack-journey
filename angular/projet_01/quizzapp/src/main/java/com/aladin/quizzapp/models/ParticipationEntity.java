package com.aladin.quizzapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Participation")
public class ParticipationEntity extends AbstractEntity {



    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "quizzId")
    private QuizzEntity quizz;

    @Column(name = "score")
    private Integer score;


    
}
