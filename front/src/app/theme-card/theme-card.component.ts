import { Component, Input, OnInit } from '@angular/core';
import { Theme } from '../core/models/theme.model';
import { Subject, takeUntil } from 'rxjs';
import { SubscriptionService } from '../core/services/subscription.service';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
})
export class ThemeCardComponent implements OnInit {
  @Input() theme?: Theme;

  private destroy$: Subject<boolean> = new Subject();
  themeId: number | undefined;
  message: String | undefined;

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {}

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
          },
          error: (error) => {
            console.error(error);
            //Gérer les erreurs
          },
        });
    }
  }
}
