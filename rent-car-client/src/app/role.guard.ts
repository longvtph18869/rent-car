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
import { User } from './user.model';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
    private location: Location
  ) {}
  user!: User;
  canActivate(route: ActivatedRouteSnapshot): boolean {
    this.authService.getUser().subscribe((user) => {
      this.user = user;
    });
    const roles = route.data['roles'] as Array<string>;
    if (
      this.authService.isLoggedIn() &&
      this.user &&
      roles.includes(this.user.role)
    ) {
      return true;
    }

    this.router.navigate(['access-denied']);
    return false;
  }
}
