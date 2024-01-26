import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { MessageResponse } from '../models/response/message-response';
import { Theme } from '../models/theme.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  private baseUrl = 'http://localhost:8080/subscriptions';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getSubscriptionForCurrentUser(): Observable<number[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<number[]>(this.baseUrl, { headers });
  }

  addSubscription(themeId: number): Observable<MessageResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post<MessageResponse>(`${this.baseUrl}/${themeId}`, '', {
      headers,
    });
  }

  getSubscribedThemes(): Observable<Theme[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<Theme[]>(`${this.baseUrl}/all`, { headers });
  }

  deleteSubscription(themeId: number) {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.delete(`${this.baseUrl}/${themeId}`, {
      headers,
    });
  }
}
