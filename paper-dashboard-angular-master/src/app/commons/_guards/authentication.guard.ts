import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {AppStateService} from '../_services/app-state.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard {

  constructor(private appStateService : AppStateService,
              private router : Router) { }


  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree{


    if(this.appStateService.authState.isAuthenticated){
      return true;
    }else {
      this.router.navigateByUrl("/login");
      return false;
    }

  }

}
