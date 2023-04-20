import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  token: string = '';
  isLoggedIn: string | null = null;
  constructor(private cookieService: CookieService) { }

  getToken() {
    this.token = this.cookieService.get('token');
  }

  saveToken(token: string) {
    this.cookieService.set('token', token, 1, '/');
  }

  deleteToken() {
    this.cookieService.delete('token');
  }

  setLoggedIn(value: string) {
    if( value === 'true') {
      localStorage.setItem('isLoggedIn', 'true');
    } 

    if( value === 'false') {
      localStorage.setItem('isLoggedIn', 'false');
    }
  }

  getLoggedIn(): string | null {
    this.isLoggedIn = localStorage.getItem('isLoggedIn');
    return this.isLoggedIn;
  }
}
