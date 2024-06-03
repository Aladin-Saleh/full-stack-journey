package com.aladin.quizzapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.dto.ChoiceDTO;
import com.aladin.quizzapp.models.ChoiceEntity;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Integer> {

    List<ChoiceDTO> findChoicesByQuestionId(Integer id);

}
