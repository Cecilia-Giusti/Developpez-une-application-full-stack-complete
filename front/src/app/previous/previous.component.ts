import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

/**
 * @component
 * @description
 * PreviousComponent provides a user interface element for navigating back to the previous page in the browser history.
 * @selector app-previous
 */
@Component({
  selector: 'app-previous',
  templateUrl: './previous.component.html',
  styleUrls: ['./previous.component.scss'],
  standalone: true,
  imports: [MatIconModule],
})
export class PreviousComponent {
  constructor() {}

  /**
   * Navigates back to the previous page in the browser's history when the back icon is clicked.
   * @param {MouseEvent} event - The mouse event triggered by clicking the back icon.
   */
  goBack(event: MouseEvent): void {
    event.preventDefault();
    window.history.back();
  }
}
