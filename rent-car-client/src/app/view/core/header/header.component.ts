import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { BehaviorSubject } from 'rxjs';
import { MenuItem } from 'primeng/api';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  loginDialogVisible: boolean = false;
  registerDialogVisible: boolean = false;
  isShow: boolean = false;
  user: any = {};
  isLoggedIn: boolean = false;

  constructor(private authService : AuthService) {}

  ngOnInit(): void {
    const isLoggedIn = this.authService.getLoggedIn();

    if(isLoggedIn === 'true') {
      this.isLoggedIn = true;
    }

  ngAfterContentChecked() {
    const userString = localStorage.getItem('user');

    if (userString !== null) {
      this.user = JSON.parse(userString);
    }
  }
  ngAfterViewChecked() {
    const isLoggedIn = localStorage.getItem('isLoggedIn');

    if (isLoggedIn !== 'true') {
      this.isLoggedIn.next(false);
    }
  }

  showLoginDialog() {
    this.loginDialogVisible = true;
  }

  showRegisterDialog() {
    this.registerDialogVisible = true;
  }

  onLoginSuccess(success: boolean) {
    this.loginDialogVisible = !success;
  }

  onDisplayRegisterDialog(show: boolean) {
    this.loginDialogVisible = !show;
    this.registerDialogVisible = show;
  }
}
