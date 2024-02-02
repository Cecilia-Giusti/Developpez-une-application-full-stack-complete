import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subject, map, of, takeUntil } from 'rxjs';
import { ArticleResponse } from 'src/app/core/models/response/article-response';
import { UserResponse } from 'src/app/core/models/response/user-response';
import { ArticleService } from 'src/app/core/services/article.service';
import { UserService } from 'src/app/core/services/user.service';

/**
 * @component
 * @description
 * DashboardComponent serves as the main user dashboard, displaying articles relevant to the current user
 * and their profile information.
 * @selector app-dashboard
 */
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit, OnDestroy {
  articles$: Observable<ArticleResponse[]> | undefined;
  user$: Observable<UserResponse> | undefined;
  isAscending: boolean = true;
  sortedArticles$: Observable<ArticleResponse[]> | undefined;
  private destroy$: Subject<boolean> = new Subject();
  errorMessage: string = '';

  /**
   * @constructor
   * @param {ArticleService} articleService - The service to interact with the article data.
   * @param {UserService} userService - The service to interact with the user data.
   */
  constructor(
    private articleService: ArticleService,
    private userService: UserService
  ) {}

  /**
   * Fetches articles and user profile on component initialization. Initializes sorted articles stream.
   */
  ngOnInit(): void {
    this.articles$ = this.articleService.getArticlesForCurrentUser();
    this.sortedArticles$ = this.sortArticles();

    this.destroy$ = new Subject<boolean>();

    this.userService
      .getProfileForCurrentUser()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response) => {
          localStorage.setItem('userId', response.id);
        },
        error: (error) => {
          this.errorMessage =
            'Une erreur est survenue. Veuillez vous reconnecter';
        },
      });
  }

  /**
   * Sorts articles based on creation date in ascending or descending order.
   * @returns {Observable<ArticleResponse[]>} A stream of sorted articles.
   */
  sortArticles(): Observable<ArticleResponse[]> {
    return this.articles$
      ? this.articles$.pipe(
          map((articles) =>
            articles.sort((a, b) => {
              let dateA = new Date(a.createdAt);
              let dateB = new Date(b.createdAt);

              return this.isAscending
                ? dateB.getTime() - dateA.getTime()
                : dateA.getTime() - dateB.getTime();
            })
          )
        )
      : of([]);
  }

  /**
   * Toggles the sorting order of articles and updates the sorted articles stream.
   */
  changeSort() {
    this.isAscending = !this.isAscending;
    this.sortedArticles$ = this.sortArticles();
  }

  /**
   * Sends a true value to `destroy$` to indicate that the component is about to be destroyed.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
