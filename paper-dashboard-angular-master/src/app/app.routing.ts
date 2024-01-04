import { Routes } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import {LoginComponent} from './commons/_components/login/login.component';
import {AuthenticationGuard} from './commons/_guards/authentication.guard';

export const AppRoutes: Routes = [

  {path: 'login', component: LoginComponent},


  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: '', component: AdminLayoutComponent, children: [
        { path: '', loadChildren: () => import('./layouts/admin-layout/admin-layout.module').then(x => x.AdminLayoutModule)}
    ]
  },
  // {path: '**', redirectTo: 'dashboard'}
   {path: '**', canActivate : [AuthenticationGuard], redirectTo: 'dashboard'}
]
