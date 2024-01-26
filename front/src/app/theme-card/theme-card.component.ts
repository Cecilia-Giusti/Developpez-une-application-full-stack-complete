import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  Output,
} from '@angular/core';
import { Theme } from '../core/models/theme.model';
import { Subject, takeUntil } from 'rxjs';
import { SubscriptionService } from '../core/services/subscription.service';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
})
export class ThemeCardComponent implements OnDestroy {
  @Input() theme?: Theme;
  @Input() isSubscriptionView: boolean = false;
  @Output() subscriptionChanged = new EventEmitter<void>();
  @Output() unsubscribeEvent = new EventEmitter<number>();

  private destroy$: Subject<boolean> = new Subject();
  themeId: number | undefined;
  message: String | undefined;
  errorMessage: String = '';

  constructor(private subscriptionService: SubscriptionService) {}

  subscribe() {
    this.destroy$ = new Subject<boolean>();
    if (this.theme) {
      this.themeId = this.theme.id;

      this.subscriptionService
        .addSubscription(this.themeId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            this.message = response.message;
            this.subscriptionChanged.emit();
            this.unsubscribeEvent.emit(this.theme?.id);
          },
          error: (error) => {
            this.errorMessage =
              error || 'Une erreur est survenue lors de votre souscription.';
          },
        });
    }
  }

  unsubscribe() {
    this.destroy$ = new Subject<boolean>();
    if (this.theme) {
      this.themeId = this.theme.id;

      this.subscriptionService
        .deleteSubscription(this.themeId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            this.unsubscribeEvent.emit(this.themeId);
          },
          error: (error) => {
            this.errorMessage =
              error ||
              'Une erreur est survenue lors du chargement de votre désabonnement.';
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
