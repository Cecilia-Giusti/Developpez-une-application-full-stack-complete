import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArticleResponse } from '../models/response/article-response';
import { Observable, catchError, of, throwError } from 'rxjs';
import { CommentResponse } from '../models/response/comment-response';
import { CommentRequest } from '../models/request/comment-request.model';
import { MessageResponse } from '../models/response/message-response';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private baseUrl = 'http://localhost:8080/articles';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getCommentsById(articleId: Number): Observable<CommentResponse[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http
      .get<CommentResponse[]>(`${this.baseUrl}/${articleId}/comments`, {
        headers,
      })
      .pipe(
        catchError((error) => {
          return this.handleErrorComment(error);
        })
      );
  }

  postComment(
    commentRequest: CommentRequest,
    articleId: Number
  ): Observable<MessageResponse> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.authService.logout();
    }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http
      .post<MessageResponse>(
        `${this.baseUrl}/${articleId}/comments`,
        commentRequest,
        {
          headers,
        }
      )
      .pipe(
        catchError((error) => {
          return this.handleErrorComment(error);
        })
      );
  }

  private handleErrorComment(error: HttpErrorResponse) {
    const status = error.status;
    let errorMessage;

    switch (status) {
      case 404:
        errorMessage = 'Une erreur est survenue. Veuillez vous reconnecter';
        break;
      default:
        errorMessage = 'Une erreur inconnue est survenue';
        break;
    }
    return throwError(errorMessage);
  }
}
