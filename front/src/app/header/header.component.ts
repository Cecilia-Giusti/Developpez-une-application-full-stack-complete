import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LogoComponent } from '../logo/logo.component';
import { NavComponent } from '../nav/nav.component';
import { PreviousComponent } from '../previous/previous.component';
import { CommonModule } from '@angular/common';

/**
 * Represents the header component of the application.
 * @component HeaderAuthComponent
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
  constructor(private router: Router) {}

  shouldShowPrevious(): boolean {
    return this.router.url === '/login' || this.router.url === '/register';
  }

  shouldShowNav(): boolean {
    return (
      this.router.url !== '/login' &&
      this.router.url !== '/register' &&
      this.router.url !== ''
    );
  }
}
