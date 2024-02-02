import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { SubscriptionService } from '../core/services/subscription.service';
import { Theme } from '../core/models/theme.model';

/**
 * @component
 * @description
 * SubscriptionComponent is responsible for displaying the list of themes the user is currently subscribed to.
 * @selector app-subscription
 */
@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.scss'],
})
export class SubscriptionComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  themes: Theme[] = [];
  errorMessage: String = '';

  /**
   * @constructor
   * @param {SubscriptionService} subscriptionService - The service to interact with the subscriptions data.
   */
  constructor(private subscriptionService: SubscriptionService) {}

  /**
   * Fetches the list of subscribed themes on component initialization.
   */
  ngOnInit(): void {
    this.getAllSubscribedThemes();
  }

  /**
   * Retrieves all themes the user is subscribed to and updates the component's state.
   */
  getAllSubscribedThemes() {
    this.destroy$ = new Subject<boolean>();
    this.subscriptionService
      .getSubscribedThemes()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response) => {
          this.themes = response;
        },
        error: (error) => {
          this.errorMessage =
            error ||
            'Une erreur est survenue lors du chargement de vos abonnements.';
        },
      });
  }

  /**
   * Handles the removal of a theme from the subscription list upon user action.
   * @param {number} themeId - The ID of the theme to be unsubscribed.
   */
  onSubscriptionChange(themeId: number) {
    this.themes = this.themes.filter((theme) => theme.id !== themeId);
  }

  /**
   * Sends a true value to `destroy$` to indicate that the component is about to be destroyed.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
