package com.aladin.quizzapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.aladin.quizzapp.models.QuizzEntity;
// import java.util.List;
// import java.time.Instant;


public interface QuizzRepository extends JpaRepository<QuizzEntity, Integer> {


    List<QuizzEntity> findQuizzEntitiesByTitle(String title);

    List<QuizzEntity> findQuizzEntitiesByTeacherUsername(String name);



}
