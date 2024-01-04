import {AfterViewInit, Component, OnInit} from '@angular/core';
import {WorkstateService} from "../../../_services/workstate.service";
import {UserMenuToggleService} from "../../../_intermediaireServices/user-menu-toggle.service";
import {AppStateService} from "../../../commons/_services/app-state.service";
import {Router} from "@angular/router";
import {TempsTravailRequest} from "../../../_models/tempsTravailRequest.model";
import {concatMap, Observable, take} from "rxjs";
import {WorktimeService} from "../../../_services/worktime.service";
import {DateFinStateService} from "../../../_services/date-fin-state.service";
import {TempsTravailResponse} from "../../../_models/tempsTravailResponse.model";
import {TempsPauseRequest} from "../../../_models/tempsPauseRequest.model";
import {TempsPauseResponse} from "../../../_models/tempsPauseResponse.model";
import {HumeurInputService} from "../../../_services/_input/humeur-input.service";
import {HumeurService} from "../../../_services/humeur.service";
import {HumeurRequest} from "../../../_models/humeurRequest.model";



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, AfterViewInit{

  public startState : Observable<boolean> = new Observable<boolean>();

  public finishState : Observable<boolean> = new Observable<boolean>();

  public startBreakState : Observable<boolean> = new Observable<boolean>();
  public finishBreakState : Observable<boolean> = new Observable<boolean>();

  public finishHumeurState : Observable<boolean> = new Observable<boolean>();

  public  tempsTravailResponse !: TempsTravailResponse
  public  tempsPauseResponse !: TempsPauseResponse
  public humeur : string = "";


  constructor(public workstateService : WorkstateService,
              public worktimeService :WorktimeService,
              public humeurInputService : HumeurInputService,
              public humeurService : HumeurService) {
  }



  ngAfterViewInit() {

    let body: string = "just verify"
    this.startState = this.workstateService.verifyStartWorkState(body);


    let body1: string = "just finish"
    this.finishState = this.workstateService.verifyFinishWorkState(body1);

    let body2: string = "just break"
    this.startBreakState = this.workstateService.verifyStartBreakState(body2);


    let body3: string = "just finish brak"
    this.finishBreakState = this.workstateService.verifyFinishBreakState(body3);


    let body4: string = "just finish humeur";
    this.finishHumeurState = this.workstateService.verifyFinishHumeurState(body4);

  }


  ngOnInit(): void {


  }


  tempsTravail : TempsTravailRequest = {
      id : 0,
      dateDebut : new Date(),
      dateFin : new Date(),
  }


  confirmerCommencerTravail() {


    this.worktimeService.saveWorkTime(this.tempsTravail).subscribe({
      next: savedWorkTime => {
        console.log("apres enregistrement : ");
        console.log(savedWorkTime);
      },
      error: err => {
        console.log(err);
      }
    })


    let body: string = "just verify"
    this.startState = this.workstateService.verifyStartWorkState(body);


    window.location.reload();


  }

  initModal(){}


  CommencerTravailHandle() {

  }

  FinirTravailHandle() {

  }

  resetEditData() {

  }



  confirmerFinirTravail() {

    console.log("finnnnnnnnnnnnnnnnnnnnnnn1")
    this.worktimeService.getTodayWorkTimeOnly().pipe(
      concatMap((value)=>{
          this.tempsTravailResponse = value;
          return this.worktimeService.finishWorkTime(this.tempsTravailResponse.id,this.tempsTravail)
        }
      )
    ).subscribe({
        next: updatedWorkTime => {
          console.log("apres la fin du travail : ");
          console.log(updatedWorkTime);
          let body: string = "just finish"
          this.finishState = this.workstateService.verifyFinishWorkState(body);
          window.location.reload();
        },
        error: err => {
          console.log(err);
        }
      }
    )

    console.log("finnnnnnnnnnnnnnnnnnnnnnn2")
  }





  CommencerReposHandle() {

  }

  FinirReposHandle() {

  }


  tempsPause : TempsPauseRequest = {
    id : 0,
    dateDebut : new Date(),
    dateFin : new Date(),
  }
  confirmerCommencerRepos() {

    this.worktimeService.saveBreakTime(this.tempsPause).subscribe({
      next: savedBreakTime => {
        console.log("apres enregistrement pause: ");
        console.log(savedBreakTime);
      },
      error: err => {
        console.log(err);
      }
    })


    let body: string = "just break"
    this.startBreakState = this.workstateService.verifyStartBreakState(body);

    window.location.reload();

  }

  confirmerFinirRepos() {

    this.worktimeService.getTodayBreakTimeOnly().pipe(
      concatMap((value)=>{
          this.tempsPauseResponse = value;
          return this.worktimeService.finishBreakTime(this.tempsPauseResponse.id,this.tempsPause)
        }
      )
    ).subscribe({
        next: updatedBreakTime => {
          console.log("apres la fin du travail : ");
          console.log(updatedBreakTime);
          let body: string = "just finish"
          this.finishState = this.workstateService.verifyFinishBreakState(body);
          window.location.reload();
        },
        error: err => {
          console.log(err);
        }
      }
    )
  }






  humeurRequest : HumeurRequest = {
    humeur : ""
  }
  soumettreHumeur() {

    this.humeurInputService.humeur$.pipe(
      concatMap((value)=>{
        this.humeurRequest.humeur = value;
        console.log("Request Humeur");
        console.log(this.humeurRequest);
          return this.humeurService.saveHumeur(this.humeurRequest);
        }
      )
    ).subscribe({
        next: savedHumeur => {
          console.log("Response Humeur");
          console.log(savedHumeur);
          let body: string = "just finish";
          this.finishHumeurState = this.workstateService.verifyFinishHumeurState(body);
          window.location.reload();

        },
        error: err => {
          console.log(err);
        }
      }
    )



  }


}
