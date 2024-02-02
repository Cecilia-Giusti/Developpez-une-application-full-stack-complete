import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ThemeResponse } from '../models/response/theme-response';
import { AuthService } from './auth.service';

/**
 * Service for managing themes.
 */
@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private baseUrl = 'http://localhost:8080/themes';

  /**
   * Creates an instance of ThemeService.
   * @param http - The HttpClient for making HTTP requests.
   * @param authService - The AuthService for authentication-related operations.
   */
  constructor(private http: HttpClient, private authService: AuthService) {}

  /**
   * Retrieves all themes.
   * @returns An Observable representing the response containing an array of themes.
   */
  getAllThemes(): Observable<ThemeResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<ThemeResponse>(this.baseUrl, { headers });
  }
}
