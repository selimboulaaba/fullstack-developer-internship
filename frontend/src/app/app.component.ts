import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    CommonModule,
    ToastrModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}
