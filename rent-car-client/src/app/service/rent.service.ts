import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RentService {
  constructor(private http: HttpClient) {}
  saveRentCar(rentCarData: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/rent', rentCarData);
  }
  getRentalsForUser(userId: number): Observable<any> {
    const params = new HttpParams().set('userId', userId);
    return this.http.get(environment.apiUrl + '/rent/rented', { params });
  }
  getRentalsForOwner(userId: number): Observable<any> {
    const params = new HttpParams().set('userId', userId);
    return this.http.get(environment.apiUrl + '/rent/lease', { params });
  }
  updateRentalStatus(rentalId: number, rentalStatus: number): Observable<void> {
    const url = `${environment.apiUrl}/rent/${rentalId}?rentalStatus=${rentalStatus}`;
    return this.http.put<void>(url, null);
  }
  getRentalsByCar(carId: number): Observable<any> {
    return this.http.get(environment.apiUrl + '/rent/car/' + carId);
  }
}
