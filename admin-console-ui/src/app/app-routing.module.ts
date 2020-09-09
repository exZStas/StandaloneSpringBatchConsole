import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ExtraOptions, RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AppComponent} from './app.component';
import {AnalyticsComponent} from './analytics/analytics.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'jobs', component: DashboardComponent },
  { path: 'analytics', component: AnalyticsComponent }
];

const routerOptions: ExtraOptions = {
  useHash: false
};

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, routerOptions)
  ]
})
export class AppRoutingModule { }
