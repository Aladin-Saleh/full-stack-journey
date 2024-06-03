package com.aladin.quizzapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.aladin.quizzapp.controllers.api.ChoiceAPI;
import com.aladin.quizzapp.dto.ChoiceDTO;
import com.aladin.quizzapp.services.ChoiceService;

@RestController
public class ChoiceController implements ChoiceAPI {


    private ChoiceService choiceService;

    @Override
    public List<ChoiceDTO> findChoicesByQuestionId(Integer id) {
        return this.choiceService.findChoicesByQuestionId(id);
    }

    @Override
    public ChoiceDTO save(ChoiceDTO choice) {
        return this.choiceService.save(choice);
    }

    @Override
    public ChoiceDTO update(ChoiceDTO choice) {
        return this.choiceService.update(choice);
    }

    @Override
    public void delete(Integer id) {
        this.choiceService.delete(id);
    }
    
}
