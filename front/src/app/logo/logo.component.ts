import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.scss'],
  standalone: true,
  imports: [RouterModule],
})
export class LogoComponent {
  @Input() page: string | undefined;

  constructor() {}
}
