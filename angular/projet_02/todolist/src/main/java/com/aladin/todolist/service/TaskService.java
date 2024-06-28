package com.aladin.todolist.service;

import com.aladin.todolist.dto.TaskDTO;

public interface TaskService {

    TaskDTO createTask(TaskDTO task);

    TaskDTO updateTask(TaskDTO task);

    void deleteTask(String taskId);

}
