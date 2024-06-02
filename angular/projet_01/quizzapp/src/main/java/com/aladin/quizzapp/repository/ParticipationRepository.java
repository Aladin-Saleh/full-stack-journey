package com.aladin.quizzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.models.ParticipationEntity;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Integer> {

}
