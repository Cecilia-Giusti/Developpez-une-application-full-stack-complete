import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-previous',
  templateUrl: './previous.component.html',
  styleUrls: ['./previous.component.scss'],
})
export class PreviousComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  goBack(event: MouseEvent): void {
    event.preventDefault();
    window.history.back();
  }
}
