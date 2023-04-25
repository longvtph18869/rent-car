import { Component } from '@angular/core';
import { HeaderComponent } from '../core/header/header.component';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css'],
})
export class MenuBarComponent {
  visible: boolean = false;

  constructor(
    private headerComponent: HeaderComponent,
    private authService: AuthService
  ) {}

  logout() {
    this.authService.logout();
    this.headerComponent.isLoggedIn = false;
  }

  showChangePasswordDialog() {
    this.visible = true;
  }
}
