import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, switchMap, takeUntil } from 'rxjs';
import { ThemeService } from '../../core/services/theme.service';
import { Theme } from '../../core/models/theme.model';
import { SubscriptionService } from '../../core/services/subscription.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss'],
})
export class ThemesComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  themes: Theme[] | undefined;
  subscriptions: number[] = [];
  errorMessage: string = '';

  constructor(
    private themeService: ThemeService,
    private subscriptionService: SubscriptionService
  ) {}

  ngOnInit(): void {
    this.getAllThemesWithSubscription();
  }

  getAllThemesWithSubscription() {
    this.destroy$ = new Subject<boolean>();
    this.themeService
      .getAllThemes()
      .pipe(
        takeUntil(this.destroy$),
        switchMap((themeResponse) => {
          this.themes = themeResponse.themes;
          return this.subscriptionService.getSubscriptionForCurrentUser();
        })
      )
      .subscribe({
        next: (subscriptionResponse) => {
          this.subscriptions = subscriptionResponse;
          this.updateSubscription();
        },
        error: (error) => {
          this.errorMessage =
            error || 'Une erreur est survenue lors du chargement des thèmes.';
        },
      });
  }

  updateSubscription(): void {
    if (this.themes && this.subscriptions) {
      this.themes.forEach((theme) => {
        theme.isSubscribed = this.subscriptions.some((subscriptionId) => {
          console.log(subscriptionId);
          return subscriptionId === theme.id;
        });
      });
    }
  }

  onSubscriptionChange(): void {
    this.getAllThemesWithSubscription();
  }

  /**
   * Sends a true value to `destroy$` to indicate that the component is about to be destroyed.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
