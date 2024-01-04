import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HumeurInputService {

  private humeurSubject : BehaviorSubject<string> = new BehaviorSubject<string>('');
  public  humeur$ : Observable<string> = this.humeurSubject.asObservable();


  setHumeur(humeur :string){
    this.humeurSubject.next(humeur);
  }

  constructor() { }
}
