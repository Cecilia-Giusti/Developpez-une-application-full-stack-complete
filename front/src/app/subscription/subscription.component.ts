import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { SubscriptionService } from '../core/services/subscription.service';
import { Theme } from '../core/models/theme.model';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.scss'],
})
export class SubscriptionComponent implements OnInit, OnDestroy {
  private destroy$: Subject<boolean> = new Subject();
  themes: Theme[] = [];
  errorMessage: String = '';

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.getAllSubscribedThemes();
  }

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

  onSubscriptionChange(themeId: number) {
    this.themes = this.themes.filter((theme) => theme.id !== themeId);
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
