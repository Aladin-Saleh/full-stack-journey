package com.aladin.todolist.service;

import com.aladin.todolist.dto.ToDoListDTO;

public interface ToDoListService {
    
    ToDoListDTO findByUser(String userId);

}
