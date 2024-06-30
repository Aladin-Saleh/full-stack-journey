package com.aladin.todolist.controller.api;

import static com.aladin.todolist.utils.Constant.API;
import static com.aladin.todolist.utils.Constant.TODOLIST_API;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.todolist.dto.ToDoListDTO;

public interface ToDoListAPI {
    

    @PostMapping(value = API + TODOLIST_API + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> create(@RequestBody ToDoListDTO toDoList);
    
    @GetMapping(value = API + TODOLIST_API, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> findByUser();
    
}
