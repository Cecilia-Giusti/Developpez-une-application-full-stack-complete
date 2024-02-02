import { Component, Input } from '@angular/core';

/**
 * @component
 * @description
 * Component for displaying an error message. This component can be used throughout the application
 * to show error messages in a consistent format.
 *  * @selector app-error-message
 */
@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.scss'],
})
export class ErrorMessageComponent {
  /**
   * The error message to display. This input allows the parent component to pass the error message text.
   */
  @Input() errorMessage?: String;
}
