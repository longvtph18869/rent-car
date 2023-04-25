import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Observable, of } from 'rxjs';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private accessToken: string | null = null;
  private tokenType = 'Bearer';
  private decodedToken: any;

  constructor(private http: HttpClient) {}

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
  getUser(): Observable<any> {
    this.decodeToken();
    const id = this.decodedToken.sub;
    return this.http.get(environment.apiUrl + '/user/' + id);
  }
}
