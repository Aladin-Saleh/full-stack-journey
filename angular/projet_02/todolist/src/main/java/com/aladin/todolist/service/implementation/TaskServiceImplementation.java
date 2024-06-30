package com.aladin.todolist.service.implementation;


import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aladin.todolist.dto.TaskDTO;
import com.aladin.todolist.dto.UserDTO;
import com.aladin.todolist.exception.ErrorCodes;
import com.aladin.todolist.exception.InvalidEntityException;
import com.aladin.todolist.model.Status;
import com.aladin.todolist.model.Task;
import com.aladin.todolist.model.ToDoList;
import com.aladin.todolist.model.User;
import com.aladin.todolist.repository.TaskRepository;
import com.aladin.todolist.repository.ToDoListRepository;
import com.aladin.todolist.service.TaskService;
import com.aladin.todolist.validator.TaskValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository repository;
    private final ToDoListRepository toDoListRepository;



    @Override
    public TaskDTO createTask(TaskDTO task) {
        UserDTO user = UserDTO.fromEntity((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ToDoList tdl = toDoListRepository.findByUserId(user.getId());


        final TaskDTO taskDTO = TaskDTO.builder()
        .content(task.getContent())
        .status(Status.NOT_COMPLETED)
        .title(task.getTitle())
        .id(task.getId())
        .toDolistId(tdl.getId())
        .build();

        // task.setToDolistId(tdl.getId());
        log.info("Creating task : {}", taskDTO);


        List<String> errors = TaskValidator.validate(taskDTO);

        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Invalid task entity", ErrorCodes.TASK_NOT_VALID, errors);
        }

        // task.setStatus(Status.NOT_COMPLETED);

        Task toSave = TaskDTO.toEntity(taskDTO);
        toSave.setToDoList(tdl);


        return TaskDTO.fromEntity(repository.save(toSave));

    }

    @Override
    public void deleteTask(String taskId) {
        TaskDTO task = TaskDTO.fromEntity(repository.findById(taskId).orElse(null));

        if (task == null) {
            throw new InvalidEntityException("Invalid task entity", ErrorCodes.TASK_NOT_FOUND);
        }

        repository.deleteById(taskId);

    }

    @Override
    public TaskDTO updateTask(TaskDTO updatedTask) {

        log.info("Updating task : {}", updatedTask);


        TaskDTO task = TaskDTO.fromEntity(repository.findById(updatedTask.getId()).orElse(null));
        UserDTO user = UserDTO.fromEntity((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ToDoList tdl = toDoListRepository.findByUserId(user.getId());

        List<String> errors = TaskValidator.validate(updatedTask);
        updatedTask.setStatus(Status.NOT_COMPLETED);



        
        if (task == null) {
            throw new InvalidEntityException("Invalid task entity", ErrorCodes.TASK_NOT_FOUND);
        }

        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Invalid task entity", ErrorCodes.TASK_NOT_VALID, errors);
        }

        
        Task toSave = TaskDTO.toEntity(updatedTask);
        toSave.setToDoList(tdl);
        



        return TaskDTO.fromEntity(repository.save(toSave));

    }

    @Override
    public void changeStatus(String taskId) {
        TaskDTO task = TaskDTO.fromEntity(repository.findById(taskId).orElse(null));
        log.info("Changing status of task : {}", task);
        UserDTO user = UserDTO.fromEntity((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ToDoList tdl = toDoListRepository.findByUserId(user.getId());



        if (task == null) {
            throw new InvalidEntityException("Invalid task entity", ErrorCodes.TASK_NOT_FOUND);
        }

        if (task.getStatus().equals(Status.NOT_COMPLETED)) {
            task.setStatus(Status.COMPLETED);
        } else {
            task.setStatus(Status.NOT_COMPLETED);
        }

        Task toSave = TaskDTO.toEntity(task);
        toSave.setToDoList(tdl);
        repository.save(toSave);

        


    }

    

    
}
