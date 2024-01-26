import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { UserResponse } from 'src/app/core/models/response/user-response';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  errorMessage: string = '';
  user: UserResponse = {
    id: '',
    username: '',
    email: '',
    created_at: new Date(),
    updated_at: new Date(),
  };

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();

    this.userService
      .getProfileForCurrentUser()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response) => {
          this.errorMessage = '';
          this.user.username = response.username;
          this.user.email = response.email;
        },
        error: (error) => {
          this.errorMessage =
            error ||
            'Une erreur est survenue lors du chargement de votre page.';
        },
      });
  }
  submit(form: NgForm) {
    this.destroy$ = new Subject<boolean>();
    if (form.valid) {
      this.userService
        .updateUser(this.user)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            localStorage.setItem('token', response.token);
            this.ngOnInit();
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
