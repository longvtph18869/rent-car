import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import jwt_decode from 'jwt-decode';
import { Token } from '@angular/compiler';
import { User } from '../user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private accessToken: string | null = null;
  private tokenType = 'Bearer';
  private decodedToken: any;
  private readonly userSubject = new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient, private router: Router) {}

  login(loginRequest: any) {
    return this.http
      .post<any>(`${environment.apiUrl}/user/login`, loginRequest)
      .pipe(
        tap((response) => {
          this.accessToken = response.accessToken;
          this.saveTokens();
        })
      );
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.JWT_TOKEN);
  }

  public getJwtToken(): string | null {
    return localStorage.getItem(this.JWT_TOKEN);
  }

  private saveTokens() {
    if (this.accessToken) {
      localStorage.setItem(
        this.JWT_TOKEN,
        `${this.tokenType} ${this.accessToken}`
      );
    }
  }

  public logout() {
    this.deleteTokens();
    localStorage.removeItem('redirectUrl');
    this.router.navigate(['/']);
    this.decodedToken = null;
  }

  private deleteJwtToken() {
    localStorage.removeItem(this.JWT_TOKEN);
  }

  private deleteTokens() {
    this.deleteJwtToken();
  }

  decodeToken(): void {
    const token = this.getJwtToken();
    if (token) {
      this.decodedToken = jwt_decode(token);
    }
  }

  getUserId() {
    this.decodeToken();
    return this.decodedToken.sub;
  }

  getUser(): Observable<User> {
    this.decodeToken();
    const id = this.decodedToken.sub;
    return this.http.get<User>(environment.apiUrl + '/user/' + id).pipe(
      tap(user => this.userSubject.next(user))
    );
  }

  getUserValue(): User | null {
    return this.userSubject.value;
  }
}
