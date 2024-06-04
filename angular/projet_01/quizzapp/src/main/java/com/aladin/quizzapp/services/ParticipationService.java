package com.aladin.quizzapp.services;

import java.util.List;

import com.aladin.quizzapp.dto.ParticipationDTO;

public interface ParticipationService {

    List<ParticipationDTO> findByQuizzId(Integer id);

    List<ParticipationDTO> findByStudentId(Integer id);

    ParticipationDTO save(ParticipationDTO participationDTO);

    ParticipationDTO update(ParticipationDTO participationDTO);

    
}
