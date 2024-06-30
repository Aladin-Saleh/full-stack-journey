package com.aladin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aladin.todolist.model.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, String> {

    @Query("FROM ToDoList t WHERE t.userId = :userId")
    ToDoList findByUserId(@Param("userId") String userId);
    
}
