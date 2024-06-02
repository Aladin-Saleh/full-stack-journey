package com.aladin.quizzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.models.TeacherEntity;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {

}
