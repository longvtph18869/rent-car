import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { AuthService } from './service/auth.service';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
    private location: Location
  ) {}

  canActivate(): boolean {
    if (!this.authService.isLoggedIn()) {
      const currentUrl = this.location.path(true);
      this.router.navigate([currentUrl], { queryParams: { openLogin: true } });
      return false;
    }
    return true;
  }
}
