import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Observable, catchError, of, throwError } from 'rxjs';
import { UserResponse } from '../models/response/user-response';
import { UpdateProfileResponse } from '../models/response/updateprofile-response';
import { AuthService } from './auth.service';

/**
 * Service for managing user-related operations.
 */
@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/user';

  /**
   * Creates an instance of UserService.
   * @param http - The HttpClient for making HTTP requests.
   * @param authService - The AuthService for authentication-related operations.
   */
  constructor(private http: HttpClient, private authService: AuthService) {}

  /**
   * Retrieves the profile of the currently logged-in user.
   * @returns An Observable representing the user's profile information.
   */
  getProfileForCurrentUser(): Observable<UserResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<UserResponse>(`${this.baseUrl}/profile`, { headers });
  }

  /**
   * Updates the user's profile information.
   * @param user - The updated user profile data.
   * @returns An Observable representing the response message and token.
   */
  updateUser(user: UserResponse): Observable<UpdateProfileResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http
      .put<UpdateProfileResponse>(`${this.baseUrl}/profile`, user, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return this.handleErrorUpdateProfil(error);
        })
      );
  }

  /**
   * Handles HTTP error responses.
   * @param error - The HTTP error response.
   * @returns An Observable with the error message.
   */
  private handleErrorUpdateProfil(error: HttpErrorResponse) {
    const status = error.status;
    let errorMessage;
    console.log(error);
    switch (status) {
      case 409:
        if (error.error === 'Username already in use') {
          errorMessage = "Ce nom d'utilisateur est déjà utilisé";
        } else {
          errorMessage = 'Cet email est déjà utilisé';
        }
        break;
      default:
        errorMessage = 'Une erreur inconnue est survenue';
        break;
    }
    return throwError(errorMessage);
  }
}
