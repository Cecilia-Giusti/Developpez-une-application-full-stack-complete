import { Component, OnInit } from '@angular/core';

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
  templateUrl: './header-auth.component.html',
  styleUrls: ['./header-auth.component.scss'],
})
export class HeaderAuthComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
