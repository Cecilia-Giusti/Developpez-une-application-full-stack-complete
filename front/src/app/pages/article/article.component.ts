import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleResponse } from '../../core/models/response/article-response';
import { ArticleService } from '../../core/services/article.service';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
})
export class ArticleComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  articleId: number | null | undefined;
  article: ArticleResponse | null = null;
  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.articleId = id ? Number(id) : null;
    this.destroy$ = new Subject<boolean>();

    if (this.articleId) {
      this.articleService
        .getArticleById(this.articleId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (articleResponse) => {
            this.article = articleResponse;
          },
          error: (error) => {
            this.errorMessage =
              error ||
              "Une erreur est survenue lors du chargement de l'article";
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
