import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AppStateService} from "./commons/_services/app-state.service";
import {Router} from "@angular/router";



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit, AfterViewInit {


  constructor(private appStateService : AppStateService,
              public router : Router) {}

  ngAfterViewInit  (){

    const currentUrl :string = window.location.href;

  }

  title!: string;

  ngOnInit(): void {
    this.appStateService.loadTokenFromLocalStorage();
  }



}
