import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { RegisterRequest } from 'src/app/core/models/request/register-request.model';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

/**
 * @component
 * @description
 * RegisterComponent provides a user interface for account registration.
 * @selector app-register
 */
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  user: RegisterRequest = {
    username: '',
    email: '',
    password: '',
  };

  errorMessage: string = '';

  /**
   * @constructor
   * @param {AuthService} authService - The service to interact with the auth data.
   * @param {Router} router - The Angular service for interacting with the router.
   */
  constructor(private authService: AuthService, private router: Router) {}

  /**
   * Handles the registration form submission. If the form is valid, it attempts to register the user.
   * @param {NgForm} form - The form containing user registration data.
   */
  submit(form: NgForm) {
    this.destroy$ = new Subject<boolean>();
    if (form.valid) {
      this.authService
        .registerUser(this.user)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            this.errorMessage = '';
            localStorage.setItem('token', response.token);
            this.router.navigate(['/dashboard']);
          },
          error: (error) => {
            this.errorMessage =
              error || 'Une erreur est survenue lors de l’inscription.';
          },
        });
    }
  }

  /**
   * Sends a true value to `destroy$` to indicate that the component is about to be destroyed.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
