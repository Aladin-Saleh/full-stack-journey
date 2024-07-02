import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {

  }

  onSubmit() {
    this.authService.login({username: this.username, password: this.password}).subscribe({
      next: (data: string) => {
        // console.log("Data received :", data);
        window.location.reload();

      },
      error: (error: string) => {
        console.error("Error on login : ", error, this.username, this.password);
      }
    })
  }

}
