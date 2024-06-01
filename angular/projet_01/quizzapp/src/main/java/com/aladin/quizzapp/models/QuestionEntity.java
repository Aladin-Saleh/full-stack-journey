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
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name = "Question")
public class QuestionEntity extends AbstractEntity {


    @Column(name= "title")
    private String title;

    @OneToMany(mappedBy = "question")
    private List<ChoiceEntity> choices;

    @ManyToOne
    @JoinColumn(name = "quizzid")
    private QuizzEntity quizz;

    @Column(name = "response")
    private ChoiceEntity response;

}
