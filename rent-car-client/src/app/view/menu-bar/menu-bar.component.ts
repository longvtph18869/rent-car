import { Component } from '@angular/core';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent {
  visible: boolean = false;

  constructor() {}

  logout() {
    localStorage.setItem('isLoggedIn', 'false');
  }

  showChangePasswordDialog() {
    this.visible = true;
  }
}
