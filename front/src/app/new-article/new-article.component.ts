import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { newArticleRequest } from '../core/models/request/newarticle-request.model';
import { ThemeService } from '../core/services/theme.service';
import { Theme } from '../core/models/theme.model';
import { ArticleService } from '../core/services/article.service';
import { MessageResponse } from '../core/models/response/message-response';
import { Router } from '@angular/router';

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

  constructor(
    private themeService: ThemeService,
    private articleService: ArticleService,
    private router: Router
  ) {}

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

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
