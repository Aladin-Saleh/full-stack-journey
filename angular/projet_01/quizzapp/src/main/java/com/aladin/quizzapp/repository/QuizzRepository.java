package com.aladin.quizzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.aladin.quizzapp.models.QuizzEntity;
// import java.util.List;
// import java.time.Instant;


public interface QuizzRepository extends JpaRepository<Integer, QuizzEntity> {



    // @Query("select * from Quizz where creationDate > :date")
    // List<QuizzEntity> findByDateGreaterThan(Instant date);


}
