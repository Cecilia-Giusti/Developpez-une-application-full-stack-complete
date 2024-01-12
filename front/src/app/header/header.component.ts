import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

/**
 * Represents the header component of the application.
 *
 * @component HeaderAuthComponent
 * @selector app-header
 *
 * @description
 * The HeaderAuthComponent is a reusable Angular component that serves as the application's site-wide header for authentification page.
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

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
