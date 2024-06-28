package com.aladin.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import com.aladin.todolist.model.ToDoList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoListDTO {

    private String id;

    private List<TaskDTO> tasks;

    private UserDTO user;

    public static ToDoList toEntity(ToDoListDTO dto) {

        if (dto == null) {
            return null;
        }
    
        ToDoList toDoList = new ToDoList();
        toDoList.setTasks(dto.getTasks().stream().map(TaskDTO::toEntity).collect(Collectors.toList()));
        toDoList.setUser(UserDTO.toEntity(dto.getUser()));
        
        return toDoList;

    }

    public static ToDoListDTO fromEntity(ToDoList entity) {

        if (entity == null) {
            return null;
        }

        ToDoListDTO toDoListDTO = new ToDoListDTO();
        toDoListDTO.setTasks(entity.getTasks().stream().map(TaskDTO::fromEntity).collect(Collectors.toList()));
        toDoListDTO.setUser(UserDTO.fromEntity(entity.getUser()));

        return toDoListDTO;
    }

    
}
