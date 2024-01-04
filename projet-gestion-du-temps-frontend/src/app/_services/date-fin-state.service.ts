import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DateFinStateService {

  constructor() { }

  private stateSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public state$: Observable<boolean> = this.stateSubject.asObservable();


  setState(state: boolean ){
    this.stateSubject.next(state);
  }
}
