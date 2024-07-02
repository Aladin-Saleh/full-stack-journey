import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { SlicePipe } from '@angular/common';
import { Task } from '../../interfaces/task';
import { CrudTaskModalComponent } from '../crud-task-modal/crud-task-modal.component';
import { TaskService } from '../../services/task.service';
import { TodolistService } from '../../services/todolist.service';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [
    NgIf,
    SlicePipe,
    CrudTaskModalComponent,
    NgFor
  ],
  templateUrl: './task.component.html',
  styleUrl: './task.component.scss'
})
export class TaskComponent {

  @Input() tasks!: Task[];
  @Input() filter!: string;

  focusedTask: Task | null = null;
  isDetailModalOpen: boolean = false;

  constructor(private taskService: TaskService, private todolistService: TodolistService) {}


  changeTaskStatus(task: Task) {
    this.taskService.changeTaskStatus(task.id).subscribe({
      next: () => {
        // Remove the task from the list of tasks
        task.status = task.status === 'COMPLETED' ? 'NOT_COMPLETED' : 'COMPLETED';
        this.todolistService.removeTask(task.id);
        this.todolistService.addTask(task);
        if (this.filter !== 'ALL') {
          this.tasks = this.tasks.filter(t => t.id !== task.id);

        }
        
      },
      error: (error) => {
        console.error('Error changing task status', error);
      }
    
    })
  }

  deleteTask(task: Task) {
    this.taskService.deleteTask(task.id).subscribe({
      next: () => {
        this.tasks = this.tasks.filter(t => t.id !== task.id);
        this.todolistService.setTask(this.tasks);
        console.log('Task deleted', task);
      },
      error: (error) => {
        console.error('Error deleting task', error);
      }
    });

  }


  openDetail(task: Task) {
    console.log(task);
    this.focusedTask = task;
    this.isDetailModalOpen = true;

  }

  closeCreateTaskModal() {
    this.isDetailModalOpen = false ;

  }
}
