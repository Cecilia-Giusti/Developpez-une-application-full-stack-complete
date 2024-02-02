import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

/**
 * @component
 * @description
 * LogoComponent displays the application's logo. It is designed as a standalone component,
 * capable of being used independently with its own encapsulated RouterModule import for potential routing.
 * The component can be customized to display differently based on the input page context.
 * @selector app-logo
 */
@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.scss'],
  standalone: true,
  imports: [RouterModule],
})
export class LogoComponent {
  /**
   * Optional input to specify the context in which the logo is being used, allowing for
   * conditional styling or behavior based on the current page or state.
   */
  @Input() page: string | undefined;

  constructor() {}
}
