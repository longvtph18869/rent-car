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
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
    private location: Location
  ) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const user = this.authService.getUserValue();
    const roles = route.data['roles'] as Array<string>;
    if (this.authService.isLoggedIn() && user && roles.includes(user.role)) {
      return true;
    }

    this.router.navigate(['access-denied']);
    return false;
  } 
}
