package com.aladin.todolist.utils;

import com.aladin.todolist.dto.TaskDTO;
import com.aladin.todolist.model.Task;
import com.aladin.todolist.model.ToDoList;
import com.aladin.todolist.repository.ToDoListRepository;

public class TaskConverter {

    public static Task toEntity(TaskDTO dto, ToDoListRepository toDoListRepository) {
        if (dto == null) {
            return null;
        }

        Task task = new Task();
        task.setId(dto.getId());
        task.setContent(dto.getContent());
        task.setStatus(dto.getStatus());
        task.setTitle(dto.getTitle());

        if (dto.getToDolistId() != null) {
            ToDoList toDoList = toDoListRepository.findById(dto.getToDolistId())
                                .orElseThrow(() -> new RuntimeException("ToDoList not found"));
            task.setToDoList(toDoList);
        }

        return task;
    }

    public static TaskDTO fromEntity(Task entity) {
        if (entity == null) {
            return null;
        }

        return TaskDTO.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .title(entity.getTitle())
            .status(entity.getStatus())
            .toDolistId(entity.getToDoList().getId())
            .build();
    }
}
