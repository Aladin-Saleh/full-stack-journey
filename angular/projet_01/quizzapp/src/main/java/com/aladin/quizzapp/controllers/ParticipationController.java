package com.aladin.quizzapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.aladin.quizzapp.controllers.api.ParticipationAPI;
import com.aladin.quizzapp.dto.ParticipationDTO;
import com.aladin.quizzapp.services.ParticipationService;


@RestController
public class ParticipationController implements ParticipationAPI {


    private ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @Override
    public List<ParticipationDTO> findByQuizzId(Integer id) {
        return this.participationService.findByQuizzId(id);
    }

    @Override
    public List<ParticipationDTO> findByStudentId(Integer id) {
        return this.participationService.findByStudentId(id);
    }

    @Override
    public ParticipationDTO save(ParticipationDTO participationDTO) {
        return this.participationService.save(participationDTO);
    }

    @Override
    public ParticipationDTO update(ParticipationDTO participationDTO) {
        return this.participationService.update(participationDTO);
    }
    
}
