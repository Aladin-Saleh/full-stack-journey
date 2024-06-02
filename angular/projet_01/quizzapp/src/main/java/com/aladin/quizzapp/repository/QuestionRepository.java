package com.aladin.quizzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.models.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>{

}
