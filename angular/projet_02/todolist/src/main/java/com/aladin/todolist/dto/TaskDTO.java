package com.aladin.todolist.dto;

import com.aladin.todolist.model.Status;
import com.aladin.todolist.model.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private String id;

    private String title;

    private String content;

    private Status status;

    private ToDoListDTO toDolist;





    public static Task toEntity(TaskDTO dto) {

        if (dto == null) {
            return null;
        }
    
        Task task = new Task();
        task.setContent(dto.getContent());
        task.setStatus(dto.getStatus());
        task.setTitle(dto.getTitle());
        task.setToDoList(ToDoListDTO.toEntity(dto.getToDolist()));
        
        
        return task;

    }


    public static TaskDTO fromEntity(Task entity) {

        if (entity == null) {
            return null;
        }

        TaskDTO task = TaskDTO.builder()
        .content(entity.getContent())
        .title(entity.getTitle())
        .status(entity.getStatus())
        .toDolist(ToDoListDTO.fromEntity(entity.getToDoList()))
        .build();

        return task;

    }

}
