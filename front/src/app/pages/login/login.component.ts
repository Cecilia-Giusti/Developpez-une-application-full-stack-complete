import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/core/models/login-request.model';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  user: LoginRequest = {
    email: '',
    password: '',
  };

  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  submit(form: NgForm) {
    if (form.valid) {
      this.authService.loginUser(this.user).subscribe({
        next: (response) => {
          (this.errorMessage = ''),
            localStorage.setItem('token', response.token);
          this.router.navigate(['/dashboard']);
          //ACCEPTER USERNAME
        },
        error: (error) => {
          this.errorMessage =
            error || 'Une erreur est survenue lors de la connexion.';
        },
      });
    }
  }
}
