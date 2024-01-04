import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { LeafletModule } from '@asymmetrik/ngx-leaflet';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

import { WorktimeComponent } from './hebergeurMode/_components/worktime/worktime.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { DashboardComponent } from './hebergeurMode/_components/dashboard/dashboard.component';

import { SidenavComponent } from './hebergeurMode/_components/sidenav/sidenav.component';

import { LogoutComponent } from './hebergeurMode/_components/logout/logout.component';

import { AujourdhuiComponent } from './hebergeurMode/_components/aujourdhui/aujourdhui.component';
import { HumeurComponent } from './hebergeurMode/_components/humeur/humeur.component';
import {MatIconModule} from "@angular/material/icon";
import { NavbarComponent } from './hebergeurMode/_components/navbar/navbar.component';
import { WidgetComponent } from './hebergeurMode/_components/widget/widget.component';

import {MatSelectModule} from "@angular/material/select";

import {MaterialFileInputModule} from "ngx-material-file-input";

import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSortModule} from '@angular/material/sort';
import { TodayComponent } from './hebergeurMode/_components/today/today.component';
import { NgChartsModule } from 'ng2-charts';

import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";

import { LoginComponent } from './commons/_components/login/login.component';
import { MdbCheckboxModule } from 'mdb-angular-ui-kit/checkbox';
import {MdbFormsModule} from "mdb-angular-ui-kit/forms";
import {MdbAccordionModule} from "mdb-angular-ui-kit/accordion";
import {MdbModalModule} from "mdb-angular-ui-kit/modal";
import {MdbCollapseModule} from "mdb-angular-ui-kit/collapse";
import {MdbCarouselModule} from "mdb-angular-ui-kit/carousel";

import { EmployeeTemplateComponent } from './hebergeurMode/_components/employee-template/employee-template.component';
import {AppHttpInterceptor} from "./commons/_interceptors/app-http.interceptor";


import {DateRangePickerModule} from "@syncfusion/ej2-angular-calendars";







//import { BsDatepickerModule } from 'ngx-bootstrap/datepicker'; // Import the Datepicker module
import {DateAdapter, MAT_DATE_FORMATS, NativeDateAdapter} from '@angular/material/core';
import { ToastrModule } from 'ngx-toastr';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {FooterComponent} from "./hebergeurMode/_components/footer/footer.component";
import { AvatarComponent } from './hebergeurMode/_components/avatar/avatar.component';
import {UserMenuComponent} from "./hebergeurMode/_components/user-menu/user-menu.component";
import { MenuItemComponent } from './hebergeurMode/_components/menu-item/menu-item.component';
import { PauseComponent } from './hebergeurMode/_components/pause/pause.component';
import { HumeurInputComponent } from './hebergeurMode/_components/_input/humeur-input/humeur-input.component';
import { ChartComponent } from './hebergeurMode/_components/chart/chart.component'; // Import the Toastr module


// import  {Input} from 'mdb-ui-kit';



@NgModule({
  declarations: [
    AppComponent,
    WorktimeComponent,
    DashboardComponent,
    SidenavComponent,
    LogoutComponent,
    AujourdhuiComponent,
    HumeurComponent,
    NavbarComponent,
    WidgetComponent,
    TodayComponent,
    FooterComponent,
    LoginComponent,
    EmployeeTemplateComponent,
    AvatarComponent,
    UserMenuComponent,
    MenuItemComponent,
    PauseComponent,
    HumeurInputComponent,
    ChartComponent






  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatIconModule,
    LeafletModule,
    MatSelectModule,
    MaterialFileInputModule,
    CdkTableModule,
    CdkTreeModule,
    MatCheckboxModule,
    MatTableModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    NgChartsModule,
    FontAwesomeModule,

    MdbCheckboxModule,
    MdbFormsModule,
    MdbAccordionModule,
    MdbModalModule,
    MdbCollapseModule,
    MdbCarouselModule,
    DateRangePickerModule,



    ToastrModule.forRoot(),
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
    }),
    MatDatepickerModule,


  ],
  exports: [],
  providers: [
    {provide : HTTP_INTERCEPTORS, useClass : AppHttpInterceptor, multi : true},
    { provide: DateAdapter, useClass: NativeDateAdapter },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
