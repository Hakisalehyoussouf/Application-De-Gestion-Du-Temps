import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Humeur} from '../models/humeur.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RapportService {

  private BASE_URL = 'http://localhost:8083';

  constructor(private http: HttpClient) { }


  GenererRapportNomPrenom(id: number): Observable<string> {
    return this.http.get<string>(`${this.BASE_URL}/getFullName/${id}`);
  }


  GenererRapportHeuresTravail(id: number): Observable<number> {
    return this.http.get<number>(`${this.BASE_URL}/getWorkHours/${id}`);
  }

  GenererRapportHeuresSupplementaires(id: number): Observable<number> {
    return this.http.get<number>(`${this.BASE_URL}/getOverHours/${id}`);
  }

}
