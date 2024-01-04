import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import jwtDecode from "jwt-decode";


@Injectable({
  providedIn: 'root'
})

export class AppStateService {

  constructor(public router : Router) { }


  // public authState : any = {
  //   isAuthenticated : false,
  //   email : undefined,
  //   roles : [],
  //   token :  "",
  //   status : "",
  //   errorMessage : "",
  //   openaiKey : "YOUR API KEY"
  // }

  public authState : any = {
    isAuthenticated : false,
    email : "",
    roles : [],
    token : "",
  }

  public setAuthState (state : any){
    this.authState = {...this.authState, ...state};
  }


  // public setAuthState (state : any){
  //   this.authState = {errorMessage:"",...this.authState, ...state};
  // }


  loadProfile(data: any) {

    let token = data['access-token'];
    let decodedToken:any = jwtDecode(token);


    this.setAuthState({
      isAuthenticated : true,
      email : decodedToken.sub,
      roles : decodedToken.scope,
      token :token
    })

    window.localStorage.setItem("token", token);
  }



  logout() {

    this.setAuthState({
      isAuthenticated : false,
      email :"",
      roles : [],
      token :""
    })

    window.localStorage.removeItem("token");
    this.router.navigateByUrl("/login")
  }

  loadTokenFromLocalStorage() {
    let token = window.localStorage.getItem("token");
    if(token){
      this.loadProfile({"access-token" : token});
    }
  }


}
