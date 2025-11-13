import { Component, signal } from '@angular/core';
import { LeftSidebarComponent } from './left-sidebar/left-sidebar.component';
import { MainComponent } from './main/main.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [LeftSidebarComponent, MainComponent],
  templateUrl: './app.html',
  styleUrl:'./app.css'
})
export class AppComponent {
  isLeftSidebarCollapsed = signal<boolean>(false);
  
}

