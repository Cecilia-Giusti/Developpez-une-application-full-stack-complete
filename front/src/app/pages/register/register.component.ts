import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { RegisterRequest } from 'src/app/core/models/register-request.model';

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

  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  submit(form: NgForm) {
    if (form.valid) {
      this.authService.registerUser(this.user).subscribe({
        next: (response) => {
          console.log('Inscription réussie', response);
          // Gérer la réponse, par exemple rediriger l'utilisateur ou afficher un message de succès
        },
        error: (error) => {
          console.error(error);
        },
      });
    }
  }
}
