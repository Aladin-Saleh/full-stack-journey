package com.aladin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aladin.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
    
}
