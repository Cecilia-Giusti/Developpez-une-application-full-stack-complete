import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, switchMap, takeUntil } from 'rxjs';
import { ThemeService } from '../../core/services/theme.service';
import { Theme } from '../../core/models/theme.model';
import { SubscriptionService } from '../../core/services/subscription.service';

/**
 * @component
 * @description
 * ThemesComponent displays a list of available themes and allows users to manage their subscriptions.
 * @selector app-themes.
 */
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

  /**
   * @constructor
   * @param {ThemeService} themeService - The service to interact with the theme data.
   * @param {SubscriptionService} subscriptionService - The service to interact with the subscriptions data.
   */
  constructor(
    private themeService: ThemeService,
    private subscriptionService: SubscriptionService
  ) {}

  /**
   * Fetches themes and subscriptions on component initialization and marks themes as subscribed/unsubscribed.
   */
  ngOnInit(): void {
    this.getAllThemesWithSubscription();
  }

  /**
   * Fetches all themes and the current user's subscriptions, then updates each theme's subscription status.
   */
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
        error: (error) => {},
      });
  }

  /**
   * Updates each theme's subscription status based on the current user's subscriptions.
   */
  updateSubscription(): void {
    if (this.themes && this.subscriptions) {
      this.themes.forEach((theme) => {
        theme.isSubscribed = this.subscriptions.some((subscriptionId) => {
          return subscriptionId === theme.id;
        });
      });
    }
  }

  /**
   * Re-fetches themes and subscriptions to reflect any changes in the user's subscriptions.
   */
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
