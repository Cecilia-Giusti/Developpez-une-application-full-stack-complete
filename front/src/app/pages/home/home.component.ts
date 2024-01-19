import { Component } from '@angular/core';

/**
 * @component HomeComponent
 * @selector app-home
 *
 * @description
 * The HomeComponent provides the main view for the user when they navigate to the root of the application.
 * It serves as the landing page of the application and is associated with the 'app-home' selector.
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  constructor() {}
}
