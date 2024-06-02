package com.aladin.quizzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.models.ChoiceEntity;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Integer> {

}
