import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ThemeResponse } from '../models/response/theme-response';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private baseUrl = 'http://localhost:8080/themes';
  constructor(private http: HttpClient, private authService: AuthService) {}

  getAllThemes(): Observable<ThemeResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<ThemeResponse>(this.baseUrl, { headers });
  }
}
