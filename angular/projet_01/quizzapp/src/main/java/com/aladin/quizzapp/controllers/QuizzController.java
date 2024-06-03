package com.aladin.quizzapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.aladin.quizzapp.controllers.api.QuizzAPI;
import com.aladin.quizzapp.dto.QuizzDTO;
import com.aladin.quizzapp.services.QuizzService;



@RestController
public class QuizzController implements QuizzAPI{


    private QuizzService quizzService;

    public QuizzController(QuizzService quizzService) {
        this.quizzService = quizzService;
    }


    @Override
    public List<QuizzDTO> findAll() {

        return quizzService.findAll();
    }

    @Override
    public List<QuizzDTO> findByTitle(String title) {
        return quizzService.findByTitle(title);
    }

    @Override
    public List<QuizzDTO> findByTeacher(String name) {
        return quizzService.findByTeacher(name);
    }

    @Override
    public QuizzDTO findById(Integer id) {
        return quizzService.findById(id);
    }

    @Override
    public QuizzDTO save(QuizzDTO quizzDTO) {
        return quizzService.save(quizzDTO);
    }

    @Override
    public QuizzDTO update(QuizzDTO quizzDTO) {
        return quizzService.update(quizzDTO);
    }

    @Override
    public void delete(Integer id) {
        quizzService.delete(id);
    }
    
}
