import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { UserResponse } from 'src/app/core/models/response/user-response';
import { UserService } from 'src/app/core/services/user.service';

/**
 * @component
 * @description
 * ProfileComponent provides an interface for users to view and update their profile information.
 * @selector app-profile
 */
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  errorMessage: string = '';
  validateMessage: string = '';
  user: UserResponse = {
    id: '',
    username: '',
    email: '',
    password: '',
    created_at: new Date(),
    updated_at: new Date(),
  };

  newPassword: string = '';
  oldPassword: string = '';

  /**
   * @constructor
   * @param {UserService} userService - The service to interact with the user data.
   */
  constructor(private userService: UserService) {}

  /**
   * Fetches the current user's profile data on component initialization.
   */
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
          this.oldPassword = response.password;
        },
        error: (error) => {
          this.errorMessage =
            error ||
            'Une erreur est survenue lors du chargement de votre page.';
        },
      });
  }

  /**
   * Handles the profile update form submission. Updates user data if the form is valid.
   * @param {NgForm} form - The form containing updated user profile data.
   */
  submit(form: NgForm) {
    this.destroy$ = new Subject<boolean>();
    if (form.valid) {
      const userToUpdate = {
        ...this.user,
        password:
          this.newPassword && this.newPassword !== undefined
            ? this.newPassword
            : this.oldPassword,
      };
      this.userService
        .updateUser(userToUpdate)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            localStorage.setItem('token', response.token);
            this.validateMessage = response.message;
            this.ngOnInit();
            this.user.password = '';
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
