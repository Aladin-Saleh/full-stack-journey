package com.aladin.todolist.service.implementation;


import org.springframework.stereotype.Service;

import com.aladin.todolist.dto.TaskDTO;
import com.aladin.todolist.dto.ToDoListDTO;
import com.aladin.todolist.repository.ToDoListRepository;
import com.aladin.todolist.service.ToDoListService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ToDoListServiceImplementation implements ToDoListService {

    private final ToDoListRepository repository;

    @Override
    public ToDoListDTO findByUser(String userId) {


        return null;
    }


    
    
    
}
