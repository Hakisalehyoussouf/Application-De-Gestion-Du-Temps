import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TempsTravailRequest} from "../_models/tempsTravailRequest.model";
import {Observable} from "rxjs";
import {TempsTravailResponse} from "../_models/tempsTravailResponse.model";

@Injectable({
  providedIn: 'root'
})
export class WorkstateService {


  constructor(private http: HttpClient) {
  }

  private href: string = 'http://localhost:8083/employee/verifies';

  verifyStartWorkState(body: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.href}/start`, body);
  }

  verifyFinishWorkState(body: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.href}/finish`, body);
  }


  verifyStartBreakState(body: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.href}/startbreak`, body);
  }

  verifyFinishBreakState(body: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.href}/finishbreak`, body);
  }


  verifyFinishHumeurState(body: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.href}/finishhumeur`, body);
  }

}

