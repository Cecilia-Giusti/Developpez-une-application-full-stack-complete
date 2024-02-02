import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ArticleResponse } from '../models/response/article-response';
import { newArticleRequest } from '../models/request/newarticle-request.model';
import { MessageResponse } from '../models/response/message-response';
import { AuthService } from './auth.service';

/**
 * Service for managing articles.
 */
@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private baseUrl = 'http://localhost:8080/articles';

  /**
   * Creates an instance of ArticleService.
   * @param http - The HttpClient for making HTTP requests.
   * @param authService - The AuthService for authentication.
   */
  constructor(private http: HttpClient, private authService: AuthService) {}

  /**
   * Gets articles for the currently logged-in user.
   * @returns An Observable of ArticleResponse[] representing the user's articles.
   */
  getArticlesForCurrentUser(): Observable<ArticleResponse[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<ArticleResponse[]>(this.baseUrl, { headers });
  }

  /**
   * Posts a new article.
   * @param articleData - The data for the new article.
   * @returns An Observable of MessageResponse representing the response message.
   */
  postArticle(articleData: newArticleRequest): Observable<MessageResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post<MessageResponse>(this.baseUrl, articleData, {
      headers,
    });
  }

  /**
   * Gets an article by its ID.
   * @param articleId - The ID of the article to retrieve.
   * @returns An Observable of ArticleResponse representing the retrieved article.
   */
  getArticleById(articleId: number): Observable<ArticleResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<ArticleResponse>(`${this.baseUrl}/${articleId}`, {
      headers,
    });
  }
}
