import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { MessageResponse } from '../models/response/message-response';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  private baseUrl = 'http://localhost:8080/subscriptions';

  constructor(private http: HttpClient) {}

  getSubscriptionForCurrentUser(): Observable<number[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      return of([]);
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<number[]>(this.baseUrl, { headers }).pipe(
      catchError((error) => {
        console.error(error);
        return of([]);
      })
    );
  }

  addSubscription(themeId: number): Observable<MessageResponse> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post<MessageResponse>(`${this.baseUrl}/${themeId}`, '', {
      headers,
    });
  }
}
