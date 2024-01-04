import { Component } from '@angular/core';
import {
  sideNavPrincipalData,
  sideNavTravailData,
  sideNavUtileData,
  sideNavUtilisateurData
} from "../../../_data/sidenav.data";
import {AppStateService} from "../../../commons/_services/app-state.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent {




  sideNavPrincpalData = sideNavPrincipalData;
  sideNavTravailData = sideNavTravailData;
  sideNavUtileData = sideNavUtileData;
  sideNavUtilisateurData = sideNavUtilisateurData;

  currentNav : any;




  constructor(public appStateService : AppStateService,
              public router : Router) {
  }

  dispatch(action: any) {
    // Remplacez cela par la logique appropriée de dispatch d'action
    console.log('Action dispatchée :', action);
  }



  logoutHandle() {
     this.appStateService.logout();
  }

}
