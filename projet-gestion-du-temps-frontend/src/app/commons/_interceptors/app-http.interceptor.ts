import { Injectable } from '@angular/core';
import {AppStateService} from "../_services/app-state.service";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";



@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  constructor(private appStateService : AppStateService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {


    if(!request.url.includes("/auth/login")){

      let newRequest = request.clone({
        headers : request.headers.set("Authorization", 'Bearer '+this.appStateService.authState.token)
      });

      return next.handle(newRequest).pipe(
        catchError(err => {
          if(err.status==401){
            this.appStateService.logout();
          }
          return throwError(err.message())
        })
      );

    }else {
      return next.handle(request);
    }

  }


}
