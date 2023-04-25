import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { HeaderComponent } from '../core/header/header.component';
import { LoginService } from 'src/app/service/login.service';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';
import { CookieService } from 'ngx-cookie-service';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  token: string = '';
  account: any = { username: '', password: '' };
  user: any = {};

  @Output() loginSuccess = new EventEmitter<boolean>();
  @Output() displayRegisterDialog = new EventEmitter<boolean>();

  constructor(
    private loginService: LoginService,
    private authService: AuthService,
    private cookieService: CookieService,
    private userService: UserService,
    private messageService: MessageService,
    private headerComponent: HeaderComponent,
    private router: Router
  ) {}
  ngOnInit(): void {}

  displayRegister() {
    this.displayRegisterDialog.emit(true);
  }

  onSubmit(event: Event) {
    event.preventDefault();
    this.authService.login(this.account).subscribe({
      next: (res) => {
        this.loginSuccess.emit(true);
        const redirectUrl = localStorage.getItem('redirectUrl');
        if (redirectUrl != null) {
          this.router.navigateByUrl(redirectUrl);
        }
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Đăng nhập không thành công',
          detail: 'Vui lòng kiểm tra lại thông tin đăng nhập và mật khẩu!',
        });
      },
    });
  }
}
