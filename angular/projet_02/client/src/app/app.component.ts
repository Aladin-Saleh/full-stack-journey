import { Component, effect } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NgIf, NgFor, AsyncPipe, JsonPipe, NgClass } from '@angular/common';
import { signal, computed } from '@angular/core';
import { TaskComponent } from './components/task/task.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthService } from './services/auth.service';
import { TodolistComponent } from './components/todolist/todolist.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, NgFor,NgClass, AsyncPipe, JsonPipe, TaskComponent, LoginComponent, RegisterComponent, TodolistComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})


export class AppComponent {

  isLogin: boolean = true;
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService) {}


  ngOnInit() {
    this.isLoggedIn = this.authService.isAuthenticated();
  }


  logout() {
    this.authService.logout();
  }

  showLogin() {
    this.isLogin = true;
  }

  showRegister() {
    this.isLogin = false;
  }



}
