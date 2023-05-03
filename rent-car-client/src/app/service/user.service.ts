import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}

  getUserByUserName(username: string): Observable<any> {
    return this.http.get<any>(environment.apiUrl + '/user/' + username);
  }

  resisterUser(user: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/user/register', user);
  }

  checkUserNameWasUsed(user: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/user/check/usernameWasUsed', user);
  }

  changePassword(id: number, changePasswordRequest: any): Observable<any> {
    const params = new HttpParams().set('id', id);
    return this.http.post(environment.apiUrl + '/user/changePassword', changePasswordRequest, { params });
  }

  saveUser(id: number, user: any): Observable<any> {
    const params = new HttpParams().set('id', id);
    return this.http.put(environment.apiUrl + '/user', user, { params });
  }
}
