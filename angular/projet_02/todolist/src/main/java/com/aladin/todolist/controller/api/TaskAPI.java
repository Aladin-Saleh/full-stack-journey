package com.aladin.todolist.controller.api;

import static com.aladin.todolist.utils.Constant.API;
import static com.aladin.todolist.utils.Constant.TASK_API;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.todolist.dto.TaskDTO;

public interface TaskAPI {


    @PostMapping(value = API + TASK_API + "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createTask(@RequestBody TaskDTO task);

    @PutMapping(value = API + TASK_API + "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object > updateTask(@RequestBody TaskDTO task);

    @DeleteMapping(value = API + TASK_API + "/delete/{taskId}")
    void deleteTask(@PathVariable String taskId);

    @GetMapping(value = API + TASK_API + "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void changeStatus(@PathVariable String taskId);
    
}
