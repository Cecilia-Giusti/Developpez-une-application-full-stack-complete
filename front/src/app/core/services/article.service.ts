import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { ArticleResponse } from '../models/response/article-response';
import { newArticleRequest } from '../models/request/newarticle-request.model';
import { MessageResponse } from '../models/response/message-response';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private baseUrl = 'http://localhost:8080/articles';

  constructor(private http: HttpClient) {}

  getArticlesForCurrentUser(): Observable<ArticleResponse[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      return of([]);
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<ArticleResponse[]>(this.baseUrl, { headers }).pipe(
      catchError((error) => {
        console.error(error);
        return of([]);
      })
    );
  }

  postArticle(articleData: newArticleRequest): Observable<MessageResponse> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post<MessageResponse>(this.baseUrl, articleData, {
      headers,
    });
  }

  getArticleById(articleId: number): Observable<ArticleResponse> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<ArticleResponse>(`${this.baseUrl}/${articleId}`, {
      headers,
    });
  }
}
