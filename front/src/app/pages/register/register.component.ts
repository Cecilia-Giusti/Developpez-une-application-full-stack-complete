import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { RegisterRequest } from 'src/app/core/models/register-request.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  user: RegisterRequest = {
    username: '',
    email: '',
    password: '',
  };

  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  submit(form: NgForm) {
    if (form.valid) {
      this.authService.registerUser(this.user).subscribe({
        next: (response) => {
          this.errorMessage = '';
          localStorage.setItem('token', response.token);
          this.router.navigate(['/dashboard']);
        },
        error: (error) => {
          console.error(error);
          this.errorMessage =
            error || 'Une erreur est survenue lors de l’inscription.';
        },
      });
    }
  }
}
