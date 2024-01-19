import { Component, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { LoginRequest } from 'src/app/core/models/request/login-request.model';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnDestroy {
  private destroy$: Subject<boolean> = new Subject();

  user: LoginRequest = {
    email: '',
    password: '',
  };

  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  submit(form: NgForm) {
    this.destroy$ = new Subject<boolean>();
    if (form.valid) {
      this.authService
        .loginUser(this.user)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
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

  /**
   * Sends a true value to `destroy$` to indicate that the component is about to be destroyed.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
