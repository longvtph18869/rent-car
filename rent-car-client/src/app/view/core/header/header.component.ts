import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { BehaviorSubject } from 'rxjs';
import { MenuItem } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  loginDialogVisible: boolean = false;
  registerDialogVisible: boolean = false;
  isShow: boolean = false;
  user: any = null;
  isLoggedIn: boolean = false;

  constructor(
    private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.activatedRoute.queryParams.subscribe((params) => {
      let openLogin = params['openLogin'];
      if (openLogin == 'true') {
        this.showLoginDialog();
      }
    });
    this.user = this.authService.getUser().subscribe({
      next: (res) => {
        this.user = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  // ngAfterContentChecked() {
  //   this.authService.getUser().subscribe(
  //     (user: any) => {
  //       this.user = user;
  //       console.log(user);
  //     },
  //     (error: any) => {
  //       console.error(error);
  //     }
  //   );
  // }
  ngAfterViewChecked() {
    this.isLoggedIn = this.authService.isLoggedIn();
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

  save(url: string) {
    localStorage.setItem('redirectUrl', url);
  }
  closeLoginDialog() {
    this.loginDialogVisible = false;
    console.log('long');
    this.router.navigate([], {
      queryParams: {
        openLogin: undefined,
      },
      queryParamsHandling: 'merge',
      replaceUrl: true,
    });
  }
  closeRegisterDialog() {
    this.registerDialogVisible = false;
  }
}
