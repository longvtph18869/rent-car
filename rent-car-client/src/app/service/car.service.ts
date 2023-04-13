import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
}
