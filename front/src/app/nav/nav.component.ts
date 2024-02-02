import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';

/**
 * @component
 * @description
 * NavComponent serves as the navigation panel for the application
 * @selector app-nav
 */
@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
  standalone: true,
  imports: [
    RouterModule,
    MatListModule,
    MatIconModule,
    MatSidenavModule,
    MatButtonModule,
    CommonModule,
  ],
})
export class NavComponent {
  /**
   * A flag to control the filler content of the sidenav, primarily for demonstration
   * or placeholder purposes.
   */
  showFiller = false;

  constructor() {}
}
