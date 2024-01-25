import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArticleResponse } from '../models/response/article-response';
import { Observable, catchError, of } from 'rxjs';
import { CommentResponse } from '../models/response/comment-response';
import { CommentRequest } from '../models/request/comment-request.model';
import { MessageResponse } from '../models/response/message-response';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private baseUrl = 'http://localhost:8080/articles';

  constructor(private http: HttpClient) {}

  getCommentsById(articleId: Number): Observable<CommentResponse[]> {
    const token = localStorage.getItem('token');

    // if (!token) {
    //   return of([]);
    // }

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    return this.http.get<CommentResponse[]>(
      `${this.baseUrl}/${articleId}/comments`,
      {
        headers,
      }
    );
  }

  postComment(
    commentRequest: CommentRequest,
    articleId: Number
  ): Observable<MessageResponse> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post<MessageResponse>(
      `${this.baseUrl}/${articleId}/comments`,
      commentRequest,
      {
        headers,
      }
    );
  }
}
