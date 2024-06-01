package com.aladin.quizzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.quizzapp.models.StudentEntity;

public interface StudentRepository extends JpaRepository<Integer,StudentEntity> {

}
