import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-previous',
  templateUrl: './previous.component.html',
  styleUrls: ['./previous.component.scss'],
  standalone: true,
  imports: [MatIconModule],
})
export class PreviousComponent {
  constructor() {}

  goBack(event: MouseEvent): void {
    event.preventDefault();
    window.history.back();
  }
}
