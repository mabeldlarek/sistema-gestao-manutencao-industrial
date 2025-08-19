import { Component, input, output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-left-sidebar',
  standalone: true,
  imports: [MatIconModule, RouterModule, CommonModule],
  templateUrl: './left-sidebar.component.html',
  styleUrls: ['./left-sidebar.component.css']
})
export class LeftSidebarComponent {
    isLeftSidebarCollapsed = input.required<boolean>();

    items = [
        {
            routeLink: 'Funcionários',
            icon: 'fal fa-home',
            label: 'Funcionários'
        },
        {
            routeLink: 'Usuários',
            icon: 'fal fa-box-open',
            label: 'Usuários'                         
        },
        {
            routeLink: 'Equipamentos',                       
            icon: 'fal fa-box',         
            label: 'Equipamentos'                                        
        },                      
        {
            routeLink: 'Peças',
            icon: 'fal fa-file',
            label: 'Peças'
        },
        {
            routeLink: 'Procedimentos',
            icon: 'fal fa-cog',
            label: 'Procedimentos'
        }
    ]

}
