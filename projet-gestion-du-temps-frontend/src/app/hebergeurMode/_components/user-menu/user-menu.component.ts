import { Component } from '@angular/core';
import {take} from "rxjs";
import {AppStateService} from "../../../commons/_services/app-state.service";
import {UserMenuToggleService} from "../../../_intermediaireServices/user-menu-toggle.service";
import {Router} from "@angular/router";
import {WorkstateService} from "../../../_services/workstate.service";

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent {


  showMenu: boolean = false;


  constructor(public workstateService : WorkstateService,
              public userMenuToggleSer : UserMenuToggleService,
              public appSatateService : AppStateService,
              private router : Router) {
  }




  toggleOpen() {

    this.userMenuToggleSer.isOpen$.pipe((take(1))).subscribe({
      next : value => {
        this.userMenuToggleSer.setisOpen(!value);
      }
    })

  }




  logoutButtonHandle() {
    this.appSatateService.logout();
  }


  tempsTravailButtonHandle() {
    this.router.navigateByUrl("/employee/worktime");

  }


}
