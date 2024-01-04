import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {WorktimeComponent} from "./hebergeurMode/_components/worktime/worktime.component";
import {DashboardComponent} from "./hebergeurMode/_components/dashboard/dashboard.component";

import {LogoutComponent} from "./hebergeurMode/_components/logout/logout.component";

import {LoginComponent} from "./commons/_components/login/login.component";
import {EmployeeTemplateComponent} from "./hebergeurMode/_components/employee-template/employee-template.component";
import {AuthenticationGuard} from "./commons/_guards/authentication.guard";


import {HumeurComponent} from "./hebergeurMode/_components/humeur/humeur.component";
import {AujourdhuiComponent} from "./hebergeurMode/_components/aujourdhui/aujourdhui.component";

const routes: Routes = [

  {path: '', redirectTo : "employee", pathMatch:"full"},



  {path: 'login', component: LoginComponent},



  /**
   * C'est simulaire pour les reservations??????
   */
  {
    path: 'employee', component: EmployeeTemplateComponent, canActivate : [AuthenticationGuard] ,children : [

      {path: '', component : DashboardComponent},
      {path: 'today', component : AujourdhuiComponent},
      {path: 'worktime', component : WorktimeComponent},
      {path : 'humeur', component : HumeurComponent },
      {path : 'logout', component : LogoutComponent}

    ]
  },

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
