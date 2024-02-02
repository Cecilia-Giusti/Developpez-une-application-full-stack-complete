import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { RegisterRequest } from 'src/app/core/models/request/register-request.model';
import { LoginRequest } from '../models/request/login-request.model';
import { Router } from '@angular/router';

/**
 * Service for managing authentication.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/auth';

  /**
   * Creates an instance of AuthService.
   * @param http - The HttpClient for making HTTP requests.
   * @param router - The Router for navigating between routes.
   */
  constructor(private http: HttpClient, private router: Router) {}

  /**
   * Registers a new user.
   * @param registerRequest - The data for user registration.
   * @returns An Observable representing the registration response.
   */
  registerUser(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, registerRequest).pipe(
      catchError((error) => {
        return this.handleErrorRegister(error);
      })
    );
  }

  /**
   * Handles registration errors and converts them into meaningful error messages.
   * @param error - The HttpErrorResponse received from the registration request.
   * @returns An Observable with an error message.
   */
  private handleErrorRegister(error: HttpErrorResponse) {
    const status = error.status;
    let errorMessage;

    switch (status) {
      case 409:
        if (error.error && error.error.includes('email')) {
          errorMessage = 'Cette adresse mail est déjà utilisée.';
        } else {
          errorMessage = "Ce nom d'utilisateur est déjà utilisé.";
        }
        break;
      case 400:
        errorMessage = "L'email ou le mot de passe est incorrect";
        break;
      default:
        errorMessage = 'Une erreur inconnue est survenue';
        break;
    }
    return throwError(errorMessage);
  }

  /**
   * Logs in a user.
   * @param loginRequest - The data for user login.
   * @returns An Observable representing the login response.
   */
  loginUser(loginRequest: LoginRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginRequest).pipe(
      catchError((error) => {
        return this.handleErrorLogin(error);
      })
    );
  }

  /**
   * Handles login errors and converts them into meaningful error messages.
   * @param error - The HttpErrorResponse received from the login request.
   * @returns An Observable with an error message.
   */
  private handleErrorLogin(error: HttpErrorResponse) {
    const status = error.status;
    let errorMessage;

    switch (status) {
      case 400:
        errorMessage =
          "L'email, le nom d'utilisateur ou le mot de passe est incorrect";
        break;
      case 401:
        errorMessage =
          "L'email, le nom d'utilisateur ou le mot de passe est incorrect";
        break;
      default:
        errorMessage = 'Une erreur inconnue est survenue';
        break;
    }
    return throwError(errorMessage);
  }

  /**
   * Logs out the user by clearing local storage and navigating to the login page.
   */
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
