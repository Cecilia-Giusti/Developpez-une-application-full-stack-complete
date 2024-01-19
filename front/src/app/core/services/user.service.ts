import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { UserResponse } from '../models/response/user-response';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient) {}

  getProfileForCurrentUser(): Observable<UserResponse> {
    const token = localStorage.getItem('token');

    //  if (!token) {
    //    return of([]);
    //  }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<UserResponse>(`${this.baseUrl}/profile`, { headers });
  }
}
