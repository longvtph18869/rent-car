import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  loginDialogVisible: boolean = false;
  registerDialogVisible: boolean = false;
  isLoggedIn: boolean = false;

  ngOnInit(): void {

  }

  showLoginDialog() {
    this.loginDialogVisible = true;
  }

  showRegisterDialog() {
    this.registerDialogVisible = true;
  }

  onLoginSuccess(success: boolean) {
    this.loginDialogVisible = !success;
    this.isLoggedIn = success;
  }

  onDisplayRegisterDialog(show: boolean) {
    this.loginDialogVisible = !show;
    this.registerDialogVisible = show;
  }
}
