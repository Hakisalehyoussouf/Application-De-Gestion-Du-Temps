import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserMenuToggleService {

  private isOpenSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public isOpen$ : Observable<boolean> = this.isOpenSubject.asObservable();

  setisOpen(isOpen : boolean){
    this.isOpenSubject.next(isOpen);
  }

  constructor() { }
}
