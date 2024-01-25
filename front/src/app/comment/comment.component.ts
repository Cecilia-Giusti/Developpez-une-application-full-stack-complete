import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { CommentResponse } from '../core/models/response/comment-response';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from '../core/services/comment.service';
import { NgForm } from '@angular/forms';
import { CommentRequest } from '../core/models/request/comment-request.model';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss'],
})
export class CommentComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  articleId: number | null | undefined;
  comments: CommentResponse[] | null = null;
  newComment: CommentRequest = {
    content: '',
  };
  message: String | undefined;

  constructor(
    private route: ActivatedRoute,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.articleId = id ? Number(id) : null;
    this.destroy$ = new Subject<boolean>();

    if (this.articleId) {
      this.commentService
        .getCommentsById(this.articleId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response: CommentResponse[]) => {
            this.comments = response;
            console.log(response[0].author);
          },
          error: (error) => {
            //gestion de l'erreur
          },
        });
    }
  }

  submit(form: NgForm) {
    const id = this.route.snapshot.params['id'];
    this.articleId = id ? Number(id) : null;
    this.destroy$ = new Subject<boolean>();
    if (form.valid && this.articleId) {
      this.commentService
        .postComment(this.newComment, this.articleId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            this.message = response.message;

            this.newComment = {
              content: '',
            };

            form.resetForm();
            this.ngOnInit();
          },
          error: (error) => {
            console.error(error);
            //Gérer les erreurs
          },
        });
    }
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }
}
