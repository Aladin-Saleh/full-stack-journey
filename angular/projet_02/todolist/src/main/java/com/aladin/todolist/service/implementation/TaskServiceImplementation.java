package com.aladin.todolist.service.implementation;


import org.springframework.stereotype.Service;

import com.aladin.todolist.dto.TaskDTO;
import com.aladin.todolist.repository.TaskRepository;
import com.aladin.todolist.service.TaskService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository repository;

    @Override
    public TaskDTO createTask(TaskDTO task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteTask(String taskId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
