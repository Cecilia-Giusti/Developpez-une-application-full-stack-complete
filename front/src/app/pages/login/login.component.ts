import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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

  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  submit(form: NgForm) {
    if (form.valid) {
      this.authService.loginUser(this.user).subscribe({
        next: (response) => {
          console.log('Connexion réussie', response);
          //TODO GET ET REDIRECTION
          //ACCEPTER USERNAME
        },
        error: (error) => {
          console.error('Erreur lors de la connexion', error);
          //TODO AFFICHER MESSAGE ERREUR DANS FRONT
        },
      });
    }
  }
}
