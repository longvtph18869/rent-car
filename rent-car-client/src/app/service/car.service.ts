import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root',
})
export class CarService {
  constructor(private http: HttpClient) {}
  getAllCars(): Observable<any> {
    return this.http.get(environment.apiUrl + '/find');
  }
  findCar(id: string): Observable<any> {
    return this.http.get(environment.apiUrl + '/car/' + id);
  }
  filter(
    lat: number,
    lng: number,
    pickupDate: Date,
    returnDate: Date
  ): Observable<any> {
    const params = new HttpParams()
      .set('latitude', lat.toString())
      .set('longitude', lng.toString())
      .set('pickupDate', pickupDate.toLocaleDateString())
      .set('returnDate', returnDate.toLocaleDateString());

    return this.http.get(environment.apiUrl + '/car/filter', { params });
  }
}
