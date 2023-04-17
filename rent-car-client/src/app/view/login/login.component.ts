import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { CookieService } from 'ngx-cookie-service'; 
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  token: string = '';
  user: any = { username: '', password: ''};

  @Output() loginSuccess = new EventEmitter<boolean>();
  @Output() displayRegisterDialog = new EventEmitter<boolean>();

  constructor(
    private loginService: LoginService,
    private cookieService: CookieService,
    private messageService: MessageService
  ) {}
  ngOnInit(): void {}

  displayRegister() {
    this.displayRegisterDialog.emit(true);
  }

  onSubmit(event: Event) {
    event.preventDefault(); 
    this.loginService.login(this.user).subscribe(
      {
        next: (res) => {
          this.loginSuccess.emit(true);
          this.token = res.tokenType + " " + res.accessToken;
          this.cookieService.set('token', this.token, 1, '/');
        },
        error: () => {
          this.messageService.add({severity:'error', 
          summary:'Đăng nhập không thành công', 
          detail:'Vui lòng kiểm tra lại thông tin đăng nhập và mật khẩu!'});
        },
      }
      
    ); 
  }
}

