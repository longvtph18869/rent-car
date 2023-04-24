import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../core/header/header.component';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {
  visible: boolean = false;
  isAdmin: boolean = false;

  ngOnInit(): void {
    const user = localStorage.getItem('user');
    const parseUser = JSON.parse(user!);

    if(parseUser.role === 'ROLE_CUSTOMER' || parseUser.role === 'ROLE_CAR_OWNER') {
      this.isAdmin = false;
    } else {
      this.isAdmin = true;
    }
  }

  constructor(private headerComponent: HeaderComponent,
              private authService: AuthService
    ) {}

  logout() {
    this.authService.setLoggedIn('false');
    this.headerComponent.isLoggedIn = false;
  }

  showChangePasswordDialog() {
    this.visible = true;
  }
}
