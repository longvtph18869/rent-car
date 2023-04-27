import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class ScheduleService {
  constructor(private http: HttpClient) {}

  findByCar(carId: any): Observable<any> {
    return this.http.get(`${environment.apiUrl}/schedule?car=${carId}`);
  }

  saveSchedule(scheduleLists: any): Observable<any> {
    return this.http.post(environment.apiUrl + '/schedule/save', scheduleLists);
  }
}
