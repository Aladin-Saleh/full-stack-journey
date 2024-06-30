package com.aladin.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.todolist.controller.api.ToDoListAPI;
import com.aladin.todolist.dto.ToDoListDTO;
import com.aladin.todolist.exception.InvalidEntityException;
import com.aladin.todolist.service.implementation.ToDoListServiceImplementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@AllArgsConstructor
@Slf4j
public class ToDoListController implements ToDoListAPI {

    private final ToDoListServiceImplementation service;

    @Override
    public ResponseEntity<Object> create(ToDoListDTO toDoList) {

        try {
            // return this.service.create(toDoList);
            return ResponseEntity.status(201).body(this.service.create(toDoList));
        } catch (InvalidEntityException e) {
            log.error("Error creating ToDoList : {}", e.getErrors());
            return ResponseEntity.badRequest().body(e.getErrors());
        }
        // return null;
    }

    @Override
    public ResponseEntity<Object> findByUser() {
            
        try {
            // return this.service.findByUser();
            return ResponseEntity.status(200).body(this.service.findByUser());
        } catch (InvalidEntityException e) {
            log.error("Error finding ToDoList : {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        // return null;
    }
    
}
