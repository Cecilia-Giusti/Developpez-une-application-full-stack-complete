import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LogoComponent } from '../logo/logo.component';
import { NavComponent } from '../nav/nav.component';
import { PreviousComponent } from '../previous/previous.component';
import { CommonModule } from '@angular/common';

/**
 * @component
 * @description
 * HeaderComponent serves as the application's global header, incorporating navigation links,
 * logo display, and conditional rendering of certain elements based on the current route.
 * It's a standalone component that directly imports required Angular modules and child components.
 * @selector app-header
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [
    RouterModule,
    LogoComponent,
    NavComponent,
    PreviousComponent,
    CommonModule,
  ],
})
export class HeaderComponent {
  /**
   * @constructor
   * @param {Router} router - The Angular Router for navigation and route detection.
   */
  constructor(private router: Router) {}

  /**
   * Determines if the 'Previous' navigation link should be displayed.
   * It's shown on specific routes like login, register, article listing, and article detail pages.
   * @returns {boolean} True if the 'Previous' link should be shown, otherwise false.
   */
  shouldShowPrevious(): boolean {
    const url = this.router.url;
    const articleIdPattern = /^\/article\/\d+$/;

    return (
      url === '/login' ||
      url === '/register' ||
      url === '/article' ||
      articleIdPattern.test(url)
    );
  }

  /**
   * Determines if the main navigation bar should be displayed.
   * It's hidden on the login, register, and root ('') routes.
   * @returns {boolean} True if the navigation bar should be shown, otherwise false.
   */
  shouldShowNav(): boolean {
    return (
      this.router.url !== '/login' &&
      this.router.url !== '/register' &&
      this.router.url !== ''
    );
  }

  /**
   * Checks if the current page is an authentication-related page (login or register).
   * @returns {boolean} True if the current route is an authentication page, otherwise false.
   */
  isAuthPage(): boolean {
    const authRoutes = ['/', '/login', '/register'];
    return authRoutes.includes(this.router.url);
  }
}
