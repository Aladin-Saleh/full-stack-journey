package com.aladin.quizzapp.services;

import java.util.List;

import com.aladin.quizzapp.dto.QuestionDTO;

public interface QuestionService {

    List<QuestionDTO> findByQuizz(Integer id);

    QuestionDTO save(QuestionDTO questionDTO);

    QuestionDTO update(QuestionDTO questionDTO);
    
    void delete(Integer id);



}
