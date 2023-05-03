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
    const formatDateTime = (date: Date): string => {
      const yyyy = date.getFullYear();
      const MM = String(date.getMonth() + 1).padStart(2, '0');
      const dd = String(date.getDate()).padStart(2, '0');
      const hh = String(date.getHours()).padStart(2, '0');
      const mm = String(date.getMinutes()).padStart(2, '0');
      const ss = String(date.getSeconds()).padStart(2, '0');
      return `${yyyy}-${MM}-${dd} ${hh}:${mm}:${ss}`;
    };

    const params = new HttpParams()
      .set('latitude', lat.toString())
      .set('longitude', lng.toString())
      .set('pickupDate', formatDateTime(pickupDate))
      .set('returnDate', formatDateTime(returnDate));

    console.log(formatDateTime(pickupDate));
    console.log(formatDateTime(returnDate));

    return this.http.get(environment.apiUrl + '/car/filter', { params });
  }

  enums(): Observable<any> {
    return this.http.get(environment.apiUrl + '/car/enums');
  }
  getAllManufacturers(): Observable<any> {
    return this.http.get(environment.apiUrl + '/car/manufacturers');
  }
  resisterCar(carData: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/car/registerCar', carData);
  }
}
