import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { newArticleRequest } from '../core/models/request/newarticle-request.model';
import { ThemeService } from '../core/services/theme.service';
import { Theme } from '../core/models/theme.model';
import { ArticleService } from '../core/services/article.service';
import { MessageResponse } from '../core/models/response/message-response';
import { Router } from '@angular/router';

/**
 * @component
 * @description
 * NewArticleComponent allows users to create new articles by providing a form
 * for article title, content, and theme selection.
 */
@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.scss'],
})
export class NewArticleComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  themes: Theme[] | undefined;
  article: newArticleRequest = {
    themeId: 0,
    title: '',
    content: '',
  };
  message: String | undefined;
  errorMessage: String = '';

  /**
   * @constructor
   * @param {Router} router - The Angular service for interacting with the router.
   * @param {ArticleService} articleService - The service to interact with the article data.
   */
  constructor(
    private themeService: ThemeService,
    private articleService: ArticleService,
    private router: Router
  ) {}

  /**
   * Fetches all themes on component initialization to populate the theme selection dropdown.
   */
  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();

    this.themeService
      .getAllThemes()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response) => {
          this.themes = response.themes;
        },
        error: (error) => {
          this.errorMessage =
            error || 'Une erreur est survenue lors du chargement des thèmes.';
        },
      });
  }

  /**
   * Submits the new article form. If valid, sends the article data to the ArticleService,
   * and on success, navigates to the dashboard.
   * @param {NgForm} form - The form containing the new article data.
   */
  submit(form: NgForm) {
    this.destroy$ = new Subject<boolean>();
    if (form.valid) {
      this.articleService
        .postArticle(this.article)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            this.message = response.message;

            this.article = {
              themeId: 0,
              title: '',
              content: '',
            };

            form.resetForm();
            this.router.navigate(['/dashboard']);
          },
          error: (error) => {
            this.errorMessage =
              error ||
              'Une erreur est survenue lors de chargement de votre nouvel article.';
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
