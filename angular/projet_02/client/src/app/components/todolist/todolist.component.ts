import { Component } from '@angular/core';
import { TodolistService } from '../../services/todolist.service';
import { Task } from '../../interfaces/task';
import { Todolist } from '../../interfaces/todolist';
import { NgIf, NgFor } from '@angular/common';
import { SlicePipe } from '@angular/common';
import { TaskComponent } from '../task/task.component';
import { CrudTaskModalComponent } from '../crud-task-modal/crud-task-modal.component';

@Component({
  selector: 'app-todolist',
  standalone: true,
  imports: [NgIf, SlicePipe, NgFor, TaskComponent, CrudTaskModalComponent],
  templateUrl: './todolist.component.html',
  styleUrl: './todolist.component.scss'
})
export class TodolistComponent {


  isTodoListExist: boolean = false;
  filteredTasks: Task[] = [];
  isCreateTaskModalOpen: boolean = false;
  currentFilter: string = 'ALL';


  constructor(private toDoListService: TodolistService) {}

  ngOnInit() {
    this.isTodoListExist = this.toDoListService.getDoListId() !== null;
    if (this.isTodoListExist) {
      this.getToDoList();

    }
  }


  openCreateTaskModal() {
    this.isCreateTaskModalOpen = true;
  }

  closeCreateTaskModal() {
    this.isCreateTaskModalOpen = false;
    this.getToDoList();

  }

  createToDoList() {
    this.toDoListService.createToDoList().subscribe({
      next: (data: Todolist) => {
        this.isTodoListExist = true;
        console.log(data);

      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  getToDoList() {
    this.toDoListService.getToDoListAPI().subscribe({
      next: (data: Todolist) => {
        this.toDoListService.setTask(data.tasks);
        this.filteredTasks = this.toDoListService.getAllTasks();


      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  filterTasks(filter: string) {
    this.currentFilter = filter;
    if (filter === 'ALL') {
      this.filteredTasks = this.toDoListService.getAllTasks();
      return;
    }
    this.filteredTasks = this.toDoListService.getAllTasks().filter((task) => task.status === filter);
    
  }





}
