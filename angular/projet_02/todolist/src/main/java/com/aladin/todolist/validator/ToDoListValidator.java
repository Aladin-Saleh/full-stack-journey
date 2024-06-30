package com.aladin.todolist.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.aladin.todolist.dto.ToDoListDTO;

public class ToDoListValidator {
    


    public static List<String> validate(ToDoListDTO toDoList) {
        List<String> errors = new ArrayList<>();
        if (toDoList == null) {
            
            errors.add("[ERROR] : ToDoList object is null");
            errors.add("[ERROR] : ToDoList user is null");
            errors.add("[ERROR] : ToDoList user id is empty");

        } else {
            
            if (!StringUtils.hasLength(toDoList.getUserId())) {
                errors.add("[ERROR] : User is null");
            }

        }
        return errors;
    }




}
