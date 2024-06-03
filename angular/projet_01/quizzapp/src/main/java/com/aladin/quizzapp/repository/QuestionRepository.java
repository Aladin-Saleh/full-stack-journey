package com.aladin.quizzapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.dto.QuestionDTO;
import com.aladin.quizzapp.models.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    List<QuestionDTO> findByQuizzId(Integer id);

}
