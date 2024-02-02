import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { CommentResponse } from '../core/models/response/comment-response';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from '../core/services/comment.service';
import { NgForm } from '@angular/forms';
import { CommentRequest } from '../core/models/request/comment-request.model';

/**
 * @component
 * @description
 * Component for displaying and submitting comments related to an article.
 * @selector app-comment
 */
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
  errorMessage: string = '';

  /**
   * @constructor
   * @param {ActivatedRoute} route - The activated route service that contains information about the route.
   * @param {CommentService} commentService - The service to interact with the comment data.
   */
  constructor(
    private route: ActivatedRoute,
    private commentService: CommentService
  ) {}

  /**
   * Fetches comments for the article on component initialization.
   */
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
          error: (error) => {},
        });
    }
  }

  /**
   * Submits a new comment if the form is valid and article ID is present.
   * @param {NgForm} form The form containing the new comment data.
   */
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
            this.errorMessage =
              error ||
              'Une erreur est survenue lors du chargement des commentaires.';
          },
        });
    }
  }

  /**
   * Sends a true value to `destroy$` to indicate that the component is about to be destroyed.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
