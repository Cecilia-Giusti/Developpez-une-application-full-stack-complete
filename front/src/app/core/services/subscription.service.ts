import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { MessageResponse } from '../models/response/message-response';
import { Theme } from '../models/theme.model';
import { AuthService } from './auth.service';

/**
 * Service for managing user subscriptions to themes.
 */
@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  private baseUrl = 'http://localhost:8080/subscriptions';

  /**
   * Creates an instance of SubscriptionService.
   * @param http - The HttpClient for making HTTP requests.
   * @param authService - The AuthService for authentication-related operations.
   */
  constructor(private http: HttpClient, private authService: AuthService) {}

  /**
   * Retrieves the subscriptions of the current user.
   * @returns An Observable representing the array of theme IDs that the user is subscribed to.
   */
  getSubscriptionForCurrentUser(): Observable<number[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<number[]>(this.baseUrl, { headers });
  }

  /**
   * Adds a subscription to a theme for the current user.
   * @param themeId - The ID of the theme to subscribe to.
   * @returns An Observable representing the response message.
   */
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

  /**
   * Retrieves all themes that the user is subscribed to.
   * @returns An Observable representing the array of subscribed themes.
   */
  getSubscribedThemes(): Observable<Theme[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<Theme[]>(`${this.baseUrl}/all`, { headers });
  }

  /**
   * Deletes a subscription to a theme for the current user.
   * @param themeId - The ID of the theme to unsubscribe from.
   * @returns An Observable representing the result of the deletion.
   */
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
