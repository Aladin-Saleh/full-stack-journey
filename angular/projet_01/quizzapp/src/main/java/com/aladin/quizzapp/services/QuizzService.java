package com.aladin.quizzapp.services;

import java.util.List;

import com.aladin.quizzapp.dto.QuizzDTO;

public interface QuizzService {


    List<QuizzDTO> findAll();

    List<QuizzDTO> findByTitle(String title);

    List<QuizzDTO> findByTeacher(String teacherName);

    QuizzDTO findById(Integer id);

    QuizzDTO save(QuizzDTO quizzDTO);

    QuizzDTO update(QuizzDTO quizzDTO);

    void delete(Integer id);
    
}
