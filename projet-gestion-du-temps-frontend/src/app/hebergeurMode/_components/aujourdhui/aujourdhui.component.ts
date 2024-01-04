
import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {TempsTravailResponse} from "../../../_models/tempsTravailResponse.model";
import {SelectionModel} from "@angular/cdk/collections";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {WorktimeService} from "../../../_services/worktime.service";
import {catchError, map, merge, Observable, of as observableOf, startWith, switchMap} from "rxjs";
import {DateFinStateService} from "../../../_services/date-fin-state.service";
import {WorkstateService} from "../../../_services/workstate.service";


@Component({
  selector: 'app-aujourdhui',
  templateUrl: './aujourdhui.component.html',
  styleUrls: ['./aujourdhui.component.css']
})
export class AujourdhuiComponent implements AfterViewInit{




  public data : TempsTravailResponse[] = [];
  public totalTravail : number = 0;
  public totalPause : number = 0;
  public supplementaires : number = 0;

  public dateDebut !: Date ;
  public dateFin !: Date ;


  length = 200;
  isLoadingResults = true;
  isRateLimitReached = false;


  // displayedColumns: string[] = ['dateDebut','dateFin', 'Total Travail', 'Total pause','Heures Supplementaires'];
  displayedColumns: string[] = ['dateDebut','dateFin'];




  selection = new SelectionModel<TempsTravailResponse>(true, []);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  public startState : Observable<boolean> = new Observable<boolean>();

  public finishState : Observable<boolean> = new Observable<boolean>();


  public startBreakState : Observable<boolean> = new Observable<boolean>();

  public finishBreakState : Observable<boolean> = new Observable<boolean>();
  public finishHumeurState : Observable<boolean> = new Observable<boolean>();



  constructor(public worktimeService: WorktimeService,
              public workstateService : WorkstateService) {}





  ngAfterViewInit() {
    //this.dataSource.paginator = this.paginator;
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 4;
    this.paginator.hidePageSize = true;
    this.paginator.showFirstLastButtons = true


    console.log("B1 : ");
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
      this.paginator.pageSize = 4;
      this.paginator.hidePageSize = true;
      this.paginator.showFirstLastButtons = true

    });



    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.worktimeService.getTodayWorkTime(
            this.sort.active,
            this.sort.direction,
            this.paginator.pageIndex,
          ).pipe(catchError(() => observableOf(null)));
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.isRateLimitReached = data === null;


          if (data === null) {
            return [];
          }

          if(data.length<this.paginator.pageSize){
            this.length = this.paginator.pageIndex * this.paginator.pageSize + data.length;
            this.paginator.hidePageSize=true;
          }

          return data;
        }),
      )
      .subscribe({
        next : data => {
          this.data = data;
          console.log("apres : ");
          console.log(data);
        }
      });
    console.log("B2 : ");

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





  loadWorkTimeData() {
    this.worktimeService.getTodayWorkTime(
      this.sort.active,
      this.sort.direction,
      this.paginator.pageIndex
    ).pipe(
      catchError(() => observableOf(null))
    ).subscribe({
      next: data => {
        this.length = this.length - 1;
        this.isLoadingResults = false;
        this.isRateLimitReached = data === null;


        if (data !== null) {
          if (this.data.length === 0 && this.paginator.pageIndex > 0) {
            this.paginator.pageIndex = this.paginator.pageIndex - 1;
          }
          this.data = data;
        }
      }
    });

  }





}
