package com.aladin.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.todolist.controller.api.TaskAPI;
import com.aladin.todolist.dto.TaskDTO;
import com.aladin.todolist.exception.InvalidEntityException;
import com.aladin.todolist.service.implementation.TaskServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@AllArgsConstructor
@Slf4j
public class TaskController implements TaskAPI {

    private final TaskServiceImplementation service;

    @Override
    public ResponseEntity<Object> createTask(TaskDTO task) {
        try {
            // return this.service.createTask(task);
            return ResponseEntity.status(201).body(this.service.createTask(task));
        } catch (InvalidEntityException e) {
            log.error("Error during creation of task {}", e.getErrors());
            return ResponseEntity.badRequest().body(e.getErrors());
        }
        // return null;
    }

    @Override
    public ResponseEntity<Object> updateTask(TaskDTO task) {
        try {
            // return this.service.updateTask(task);
            return ResponseEntity.status(200).body(this.service.updateTask(task));
        } catch (InvalidEntityException e) {
            log.error("Error during update of task {}", e.getErrors(), e);
            return ResponseEntity.badRequest().body(e.getErrors());
        }
        // return null;
    }

    @Override
    public void deleteTask(String taskId) {
        try {
            this.service.deleteTask(taskId);
        } catch (InvalidEntityException e) {
            log.error("Error during deletion of task {}", taskId, e);
        }
    }

    @Override
    public void changeStatus(String taskId) {

        try {
            this.service.changeStatus(taskId);
        } catch (InvalidEntityException e) {
            log.error("Error during changing status of task {}", e.getErrors());
        }


    }
    
}
