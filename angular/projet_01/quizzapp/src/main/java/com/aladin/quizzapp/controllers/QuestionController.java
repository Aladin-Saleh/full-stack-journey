package com.aladin.quizzapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.aladin.quizzapp.controllers.api.QuestionAPI;
import com.aladin.quizzapp.dto.QuestionDTO;
import com.aladin.quizzapp.services.QuestionService;

@RestController
public class QuestionController implements QuestionAPI {


    private QuestionService questionService;

    @Override
    public List<QuestionDTO> findByQuizz(Integer id) {
        return this.questionService.findByQuizz(id);
    }

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {
        return this.questionService.save(questionDTO);
    }

    @Override
    public QuestionDTO update(QuestionDTO questionDTO) {
        return this.questionService.update(questionDTO);
    }

    @Override
    public void delete(Integer id) {
        this.questionService.delete(id);
    }
    
}
