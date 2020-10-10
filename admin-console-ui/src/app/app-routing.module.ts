import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ExtraOptions, RouterModule, Routes} from '@angular/router';
import {AnalyticsComponent} from './components/analytics/analytics.component';
import {HomeComponent} from './components/home/home.component';
import {JobsComponent} from './components/jobs/jobs.component';
import {JobDetailsComponent} from './components/job-details/job-details.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'jobs', component: JobsComponent },
  { path: 'analytics', component: AnalyticsComponent },
  { path: 'jobs/:jobId', component: JobDetailsComponent }
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
