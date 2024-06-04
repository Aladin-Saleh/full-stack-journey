package com.aladin.quizzapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.dto.ParticipationDTO;
import com.aladin.quizzapp.models.ParticipationEntity;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Integer> {


    List<ParticipationDTO> findByQuizzId(Integer id);

    List<ParticipationDTO> findByStudentId(Integer id);


}
