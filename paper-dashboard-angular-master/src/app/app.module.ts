
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


import { SidebarModule } from './sidebar/sidebar.module';
import { FooterModule } from './shared/footer/footer.module';
import { NavbarModule} from './shared/navbar/navbar.module';
import { FixedPluginModule} from './shared/fixedplugin/fixedplugin.module';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routing';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import {DateAdapter, NativeDateAdapter} from '@angular/material/core';
import {AppHttpInterceptor} from './commons/_interceptors/app-http.interceptor';
import {LoginComponent} from './commons/_components/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(AppRoutes, {
      useHash: true
    }),
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    SidebarModule,
    NavbarModule,
    ToastrModule.forRoot(),
    FooterModule,
    FixedPluginModule,
    ReactiveFormsModule
  ],
  exports: [],
  providers: [
    {provide : HTTP_INTERCEPTORS, useClass : AppHttpInterceptor, multi : true},
    { provide: DateAdapter, useClass: NativeDateAdapter },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
