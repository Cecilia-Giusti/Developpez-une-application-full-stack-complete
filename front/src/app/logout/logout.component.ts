import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

/**
 * @component
 * @description
 * LogoutComponent provides a UI element for users to initiate the logout process.
 * It clears the local storage (presumably to remove authentication tokens) and redirects
 * the user to the application's root page. It's a standalone component with RouterModule
 * imported for routing capabilities.
 * @selector app-logout
 */
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss'],
  standalone: true,
  imports: [RouterModule],
})
export class LogoutComponent {
  /**
   * @constructor
   * @param {Router} router - The Angular Router for navigation.
   */
  constructor(private router: Router) {}

  /**
   * Clears user session data from local storage and navigates to the root page.
   * This function is intended to be called when the user clicks the logout button or link.
   */
  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
