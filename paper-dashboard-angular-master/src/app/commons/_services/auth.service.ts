import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private host: string = 'http://localhost:8083';

  constructor(private http : HttpClient) { }


  public login(email : string, password : string){

    let options = {
      headers : new HttpHeaders().set("Content-Type", "application/x-www-form-urlencoded")
    }

    let params = new HttpParams()
      .set("email", email)
      .set("password", password);

    return this.http.post(`${this.host}/auth/login`, params, options); // Cette logique est a remplacer par la vraie
  }

}
