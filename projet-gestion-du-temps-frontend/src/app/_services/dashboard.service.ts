import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private nbreAnnonceSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreAnnonce$ : Observable<number> = this.nbreAnnonceSubject.asObservable();
  setNbreAnnonce(nbre : number){
    this.nbreAnnonceSubject.next(nbre);
  }



  private nbreReservationSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreReservation$ : Observable<number> = this.nbreReservationSubject.asObservable();
  setNbreReservation(nbre : number){
    this.nbreReservationSubject.next(nbre);
  }

  private nbreTodayReservationSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreTodayReservation$ : Observable<number> = this.nbreTodayReservationSubject.asObservable();
  setNbreTodayReservation(nbre : number){
    this.nbreTodayReservationSubject.next(nbre);
  }




  private nbreTransactionSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreTransaction$ : Observable<number> = this.nbreTransactionSubject.asObservable();
  setNbreTransaction(nbre : number){
    this.nbreTransactionSubject.next(nbre);
  }

  private nbreTodayTransactionSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreTodayTransaction$ : Observable<number> = this.nbreTodayTransactionSubject.asObservable();
  setNbreTodayTransaction(nbre : number){
    this.nbreTodayTransactionSubject.next(nbre);
  }




  private nbreReclamationSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreReclamation$ : Observable<number> = this.nbreReclamationSubject.asObservable();
  setNbreReclamation(nbre : number){
    this.nbreReclamationSubject.next(nbre);
  }

  private nbreTodayReclamationSubject : BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public nbreTodayReclamation$ : Observable<number> = this.nbreTodayReclamationSubject.asObservable();
  setNbreTodayReclamation(nbre : number){
    this.nbreTodayReclamationSubject.next(nbre);
  }




  constructor() { }

}
