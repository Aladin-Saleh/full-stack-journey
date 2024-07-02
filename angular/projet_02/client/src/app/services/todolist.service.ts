import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Todolist } from '../interfaces/todolist';
import { Task } from '../interfaces/task';



@Injectable({
  providedIn: 'root'
})
export class TodolistService {

  private readonly API_URL = 'http://localhost:8080/api/todolist';
  private allTasks: Task[] = [];
  

  constructor(private http: HttpClient) { }


  createToDoList() : Observable<Todolist>  {

    if (this.isBrowser()) {
      const todolistId = localStorage.getItem('todolistId');
      if (todolistId) {
        return new Observable(observer => observer.error('To do list already exist'));
      }
    }

    return this.http.post<Todolist>(`${this.API_URL}/create`, {}).pipe(
      tap(
        (response) => {
          if (this.isBrowser()) {
            localStorage.setItem("todolistId", response.id);
          }


        }
      )
    );
  }

  getToDoListAPI() : Observable<Todolist> {
    return this.http.get<Todolist>(`${this.API_URL}`);
  }


  getDoListId(): string | null {
    return this.isBrowser() ? localStorage.getItem('todolistId') : null;
  }


  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
  }


  getAllTasks(): Task[] {
    return this.allTasks;
  }

  setTask(tasks: Task[]) {
    this.allTasks = tasks;
  }

  removeTask(taskId: string) {
    this.allTasks = this.allTasks.filter(task => task.id !== taskId);
  }

  addTask(task: Task) {
    this.allTasks.push(task);
  }
}
