package com.aladin.todolist.service.implementation;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aladin.todolist.dto.TaskDTO;
import com.aladin.todolist.dto.ToDoListDTO;
import com.aladin.todolist.dto.UserDTO;
import com.aladin.todolist.exception.ErrorCodes;
import com.aladin.todolist.exception.InvalidEntityException;
import com.aladin.todolist.model.User;
import com.aladin.todolist.repository.ToDoListRepository;
import com.aladin.todolist.service.ToDoListService;
import com.aladin.todolist.validator.ToDoListValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class ToDoListServiceImplementation implements ToDoListService {

    private final ToDoListRepository repository;

    @Override
    public ToDoListDTO findByUser() {
        UserDTO user = UserDTO.fromEntity((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        log.info("User from header : {}", user);

        if (user == null) {
            throw new InvalidEntityException("User is null", ErrorCodes.USER_NOT_FOUND);
        }

        ToDoListDTO toDoList = ToDoListDTO.fromEntity(repository.findByUserId(user.getId()));
        log.info("ToDoList : {}", toDoList);

        if (toDoList == null) {
            throw new InvalidEntityException("ToDoList not found", ErrorCodes.TODOLIST_NOT_FOUND);
        }


        return toDoList;


    }

    @Override
    public ToDoListDTO create(ToDoListDTO toDoList) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        List<TaskDTO> tasks = new ArrayList<>();
        toDoList.setUserId(user.getId());
        toDoList.setTasks(tasks);

        List<String> errors = ToDoListValidator.validate(toDoList);

        

        log.info("User from header : {}", toDoList);


        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Invalid ToDoList entity", ErrorCodes.TODOLIST_NOT_VALID, errors);
        }


        return ToDoListDTO.fromEntity(repository.save(ToDoListDTO.toEntity(toDoList)));




    }


    
    
    
}
