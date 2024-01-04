import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {TempsTravailResponse} from "../../../_models/tempsTravailResponse.model";
import {SelectionModel} from "@angular/cdk/collections";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {WorktimeService} from "../../../_services/worktime.service";
import {catchError, map, merge, of as observableOf, startWith, switchMap} from "rxjs";
import {TempsPauseResponse} from "../../../_models/tempsPauseResponse.model";

@Component({
  selector: 'app-pause',
  templateUrl: './pause.component.html',
  styleUrls: ['./pause.component.css']
})
export class PauseComponent implements AfterViewInit{



  public data : TempsPauseResponse[] = [];

  public dateDebut !: Date ;
  public dateFin !: Date ;


  length = 200;
  isLoadingResults = true;
  isRateLimitReached = false;


  displayedColumns: string[] = ['dateDebut','dateFin'];


  selection = new SelectionModel<TempsPauseResponse>(true, []);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(public worktimeService: WorktimeService) {}





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
          return this.worktimeService.getTodayBreakTime(
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

  }



  loadWorkTimeData_p() {
    this.worktimeService.getTodayBreakTime(
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
