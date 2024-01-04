import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Humeur } from '../models/humeur.model';

@Injectable({
  providedIn: 'root'
})
export class HumeurService {
  private baseUrl = 'http://localhost:8083';

  constructor(private http: HttpClient) { }

  getAllHumeurs(): Observable<Humeur[]> {
    return this.http.get<Humeur[]>(`${this.baseUrl}/getAllHumeurs`);
  }


}
