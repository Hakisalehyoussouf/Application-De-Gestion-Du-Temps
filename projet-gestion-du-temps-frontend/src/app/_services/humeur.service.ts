import { Injectable } from '@angular/core';
import {TempsTravailRequest} from "../_models/tempsTravailRequest.model";
import {Observable} from "rxjs";
import {TempsTravailResponse} from "../_models/tempsTravailResponse.model";
import {HttpClient} from "@angular/common/http";
import {HumeurResponse} from "../_models/humeurResponse.model";
import {HumeurRequest} from "../_models/humeurRequest.model";

@Injectable({
  providedIn: 'root'
})
export class HumeurService {

  constructor(private http:HttpClient) { }
  private href: string = 'http://localhost:8083/employee/humeur';


  public saveHumeur(h :HumeurRequest):Observable<HumeurResponse>{
    return this.http.post<HumeurResponse>(`${this.href}`, h)
  }




}
