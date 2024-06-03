package com.aladin.quizzapp.services;

import java.util.List;

import com.aladin.quizzapp.dto.ChoiceDTO;

public interface ChoiceService {

    List<ChoiceDTO> findChoicesByQuestionId(Integer id);

    ChoiceDTO save(ChoiceDTO choice);

    ChoiceDTO update(ChoiceDTO choice);

    void delete(Integer id);


    
}
