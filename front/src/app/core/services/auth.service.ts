import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { RegisterRequest } from 'src/app/core/models/request/register-request.model';
import { LoginRequest } from '../models/request/login-request.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient, private router: Router) {}

  registerUser(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, registerRequest).pipe(
      catchError((error) => {
        return this.handleErrorRegister(error);
      })
    );
  }

  private handleErrorRegister(error: HttpErrorResponse) {
    const status = error.status;
    let errorMessage;

    switch (status) {
      case 409:
        errorMessage = 'Cette adresse mail est déjà utilisée.';
        break;
      case 400:
        errorMessage = "L'email ou le mot de passe est incorrect";
        break;
      default:
        errorMessage = 'Une erreur inconnue est survenue';
        break;
    }

    console.error(errorMessage);
    return throwError(errorMessage);
  }

  loginUser(loginRequest: LoginRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginRequest).pipe(
      catchError((error) => {
        console.log(error);
        return this.handleErrorLogin(error);
      })
    );
  }

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

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
