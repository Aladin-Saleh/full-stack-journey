package com.aladin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.todolist.model.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, String> {
    
}
