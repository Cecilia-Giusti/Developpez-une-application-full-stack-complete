import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
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

    return this.http.put<UpdateProfileResponse>(
      `${this.baseUrl}/profile`,
      user,
      {
        headers,
      }
    );
  }
}
