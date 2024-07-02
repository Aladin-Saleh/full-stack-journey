import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { TaskService } from '../../services/task.service';
import { TodolistService } from '../../services/todolist.service';
import { Task } from '../../interfaces/task';
import { cp } from 'fs';

@Component({
  selector: 'app-crud-task-modal',
  standalone: true,
  imports: [ FormsModule, NgIf],
  templateUrl: './crud-task-modal.component.html',
  styleUrl: './crud-task-modal.component.scss'
})
export class CrudTaskModalComponent {

  @Input() isCreateTaskModalOpen: boolean = false;
  @Input() isDetailModalOpen: boolean = false;
  @Input() focusedTask!: Task | null;


  @Output() closeCreateTaskModalEvent = new EventEmitter<boolean>();

  newTask: any = { title: '', content: '' };
  isUpdateEnable: boolean = false;


  constructor(private taskService: TaskService, private todolistService: TodolistService) {}

  ngOnInit() {
    if (this.focusedTask !== null && this.focusedTask !== undefined) {
      console.log(this.focusedTask);
      this.newTask.title = this.focusedTask.title;
      this.newTask.content = this.focusedTask.content;
    }
  }

  enableUpdateButton() {
    this.isUpdateEnable = true;
  
  }


  closeModal() {
    this.closeCreateTaskModalEvent.emit(false);
  }


  deleteTask() {
    if (this.focusedTask !== null) {
      this.taskService.deleteTask(this.focusedTask.id).subscribe({
        next: (data) => {
          console.log(data);
          this.todolistService.removeTask(this.focusedTask !== null ? this.focusedTask.id : '');
          this.closeModal();
        },
        error: (error) => {
          console.error(error);
        }
      });
    }
  } 

  updateTask() {
    if (this.focusedTask !== null) {
      this.taskService.updateTask({ id: this.focusedTask.id, title: this.newTask.title, content: this.newTask.content, toDolistId: this.focusedTask.toDolistId }).subscribe({
        next: (data) => {
          console.log(data);
          this.closeModal();
        },
        error: (error) => {
          console.error(error);
        }
      });
    }
  }

  createTask() {
    // TODO: Add validation
    this.taskService.createTask(this.newTask).subscribe({
      next: (data) => {
        console.log(data);
        this.todolistService.addTask(data);
        this.closeModal();
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

}
