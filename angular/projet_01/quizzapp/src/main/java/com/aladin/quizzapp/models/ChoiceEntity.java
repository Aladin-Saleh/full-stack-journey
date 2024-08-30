package com.aladin.quizzapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "Choice")
public class ChoiceEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "questionId")
    private QuestionEntity question;

    @Column(name = "content")
    private String content;

    @OneToOne(mappedBy = "response")
    private QuestionEntity responseForQuestion;

    
}
