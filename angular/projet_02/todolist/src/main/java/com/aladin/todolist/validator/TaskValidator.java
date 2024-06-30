package com.aladin.todolist.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.aladin.todolist.dto.TaskDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskValidator {


    public static List<String> validate(TaskDTO task) {
        List<String> errors = new ArrayList<>();
        log.info("Validating task entity : {}", task);
        if (task == null) {
            
            errors.add("[ERROR] : Task object is null");
            errors.add("[ERROR] : Task title is empty");
            errors.add("[ERROR] : Task content is empty");
            errors.add("[ERROR] : Task toDolist is null");

        } else {
        
            if (!StringUtils.hasLength(task.getTitle())) {
                errors.add("[ERROR] : Task title is empty");
            }

            if (!StringUtils.hasLength(task.getContent())) {
                errors.add("[ERROR] : Task content is empty");
            }

            if (!StringUtils.hasLength(task.getToDolistId())) {
                
                errors.add("[ERROR] : Task toDolist is null" );
            }
        }
        return errors;
    }
    
}
