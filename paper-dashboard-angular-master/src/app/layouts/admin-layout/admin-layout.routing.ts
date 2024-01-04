import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import {EmployeeComponent} from "../../pages/Employee/employee.component";
import {HumeurComponent} from "../../pages/humeur/humeur.component";


export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user',           component: UserComponent },
    { path: 'employee',       component: EmployeeComponent },
    { path: 'humeurs',       component: HumeurComponent },


];
