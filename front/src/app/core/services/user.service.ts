import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Observable, catchError, of, throwError } from 'rxjs';
import { UserResponse } from '../models/response/user-response';
import { MessageResponse } from '../models/response/message-response';
import { UpdateProfileResponse } from '../models/response/updateprofile-response';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getProfileForCurrentUser(): Observable<UserResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<UserResponse>(`${this.baseUrl}/profile`, { headers });
  }

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
