import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {RouterModule} from '@angular/router';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {SidenavModule} from 'angular-ng-sidenav';
import { AnalyticsComponent } from './components/analytics/analytics.component';
import { HomeComponent } from './components/home/home.component';
import {JobsComponent} from './components/jobs/jobs.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, NgForm} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    JobsComponent,
    AnalyticsComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    BrowserAnimationsModule,
    NgbModule,
    SidenavModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
