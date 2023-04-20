import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  login(loginRequest: any): Observable<any> {
    return this.http.post<any>(
      environment.apiUrl + '/user/login',
      loginRequest
    );
  }
}
