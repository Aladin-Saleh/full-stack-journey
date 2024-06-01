package com.aladin.quizzapp.services;

import java.util.List;

import com.aladin.quizzapp.dto.QuizzDTO;

public interface QuizzService {


    List<QuizzDTO> findAll();

    List<QuizzDTO> findByTitle(String title);

    QuizzDTO findById(Integer id);
    
}
