import { Component, OnInit, HostListener  } from '@angular/core';
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

    if(this.isLoggedIn) {
      this.user = this.authService.getUser().subscribe({
        next: (res) => {
          this.user = res;
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }

  @HostListener('document:click', ['$event'])
  onClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    const isDropDownMenuButton = target.matches('#dropdownMenuButton');
    if (!isDropDownMenuButton) {
      this.isShow = false;
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
    this.isLoggedIn = this.authService.isLoggedIn();
    console.log(this.authService.getUserValue());
    this.user = this.authService.getUser().subscribe({
      next: (res) => {
        this.user = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
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
