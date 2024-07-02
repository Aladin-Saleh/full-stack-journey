import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Task } from '../interfaces/task';



@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly API_URL = 'http://localhost:8080/api/task';

  constructor(private http: HttpClient) { }



  createTask(task: { title: string, content: string }): Observable<Task> {
    return this.http.post<Task>(`${this.API_URL}/create`, task);
  }


  updateTask(task: { id: string, title: string, content: string, toDolistId: string }): Observable<Task> {
    return this.http.put<Task>(`${this.API_URL}/update`, task);
  }


  changeTaskStatus(taskId: string): Observable<Task> {
    return this.http.get<Task>(`${this.API_URL}/${taskId}`);
  }

  deleteTask(taskId: string): Observable<Task> {
    return this.http.delete<Task>(`${this.API_URL}/delete/${taskId}`);
  }  


}
