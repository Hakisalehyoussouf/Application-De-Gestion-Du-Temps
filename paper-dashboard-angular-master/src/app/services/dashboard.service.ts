import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseUrl = 'http://localhost:8083';

  constructor(private http: HttpClient) { }

  getUserCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/dashboard`);
  }

  getTotalMoods(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/totalMoods`);
  }


}
