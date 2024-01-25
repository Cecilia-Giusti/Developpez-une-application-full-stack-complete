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
  @Output() subscriptionChanged = new EventEmitter<number>();

  private destroy$: Subject<boolean> = new Subject();
  themeId: number | undefined;
  message: String | undefined;

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
            console.log(this.message);
            this.subscriptionChanged.emit(this.themeId);
          },
          error: (error) => {
            console.error(error);
            //Gérer les erreurs
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
