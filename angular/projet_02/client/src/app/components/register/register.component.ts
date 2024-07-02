import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, NgClass],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  email: string = '';

  message: string = '';
  isErrorMessage: boolean = false;

  constructor(private authService: AuthService) { }



  onSubmit() {
    this.authService.register({ username: this.username, email: this.email, password: this.password }).subscribe({
      next: (data: any) => {
        console.log("Data received :", data);
        this.message = data.message;
        this.isErrorMessage = false;
        // window.location.reload();

      },
      error: (error: any) => {
        console.error("Error on register : ", error, this.username, this.email, this.password);
        this.message = error.error.message;
        this.isErrorMessage = true;

      }
    });
  }
}
