import { HttpClient, HttpParams } from '@angular/common/http';
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
  updateCar(carData: any): Observable<any> {
    return this.http.put(environment.apiUrl + '/car/updateCar', carData);
  }
  updateStatus(id: any, status: any): Observable<any> {
    const params = new HttpParams().set('status', status);
    return this.http.put(environment.apiUrl + '/car/' + id, params);
  }
}
