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

/**
 * Service for managing comments on articles.
 */
@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private baseUrl = 'http://localhost:8080/articles';

  /**
   * Creates an instance of CommentService.
   * @param http - The HttpClient for making HTTP requests.
   * @param authService - The AuthService for authentication-related operations.
   */
  constructor(private http: HttpClient, private authService: AuthService) {}

  /**
   * Retrieves comments for a specific article by its ID.
   * @param articleId - The ID of the article for which to retrieve comments.
   * @returns An Observable representing the comments for the article.
   */
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

  /**
   * Posts a new comment on a specific article.
   * @param commentRequest - The data for the new comment.
   * @param articleId - The ID of the article on which to post the comment.
   * @returns An Observable representing the response message.
   */
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

  /**
   * Handles comment-related errors and converts them into meaningful error messages.
   * @param error - The HttpErrorResponse received from the comment request.
   * @returns An Observable with an error message.
   */
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
