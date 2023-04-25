import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OwnerService {
  constructor(private http: HttpClient) {}
  myCars(userId: any): Observable<any> {
    return this.http.get(`${environment.apiUrl}/owner/mycars?user=${userId}`);
  }
}
