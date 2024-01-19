import { Component } from '@angular/core';
import { Observable, Subject, map, of, takeUntil } from 'rxjs';
import { ArticleResponse } from 'src/app/core/models/response/article-response';
import { UserResponse } from 'src/app/core/models/response/user-response';
import { ArticleService } from 'src/app/core/services/article.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent {
  articles$: Observable<ArticleResponse[]> | undefined;
  user$: Observable<UserResponse> | undefined;
  isAscending: boolean = true;
  sortedArticles$: Observable<ArticleResponse[]> | undefined;
  private destroy$: Subject<boolean> = new Subject();

  constructor(
    private articleService: ArticleService,
    private userService: UserService
  ) {}

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
          console.log(error);
        },
      });
  }

  sortArticles(): Observable<ArticleResponse[]> {
    return this.articles$
      ? this.articles$.pipe(
          map((articles) =>
            articles.sort((a, b) => {
              let dateA = new Date(a.createdAt);
              let dateB = new Date(b.createdAt);

              return this.isAscending
                ? dateA.getTime() - dateB.getTime()
                : dateB.getTime() - dateA.getTime();
            })
          )
        )
      : of([]);
  }

  changeSort() {
    this.isAscending = !this.isAscending;
    this.sortedArticles$ = this.sortArticles();
  }
}
