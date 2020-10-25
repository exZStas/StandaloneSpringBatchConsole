import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, RouterStateSnapshot, Resolve, Router} from '@angular/router';
import { from } from 'rxjs';
import { Observable } from 'rxjs';
import {JobExecution} from '../model/job-execution';
import { take } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import {JobExecutionsService} from '../services/jobs-executions.service';

@Injectable({
  providedIn: 'root'
})
export class JobExecutionResolver implements Resolve<JobExecution | boolean> {

  constructor(private jobExecutionsService: JobExecutionsService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JobExecution | boolean> {
    let jobExecutionId = route.paramMap.get('id')
    return this.jobExecutionsService.getJobExecutionById(Number(jobExecutionId))
      .pipe(take(1), catchError(() => from(this.router.navigate(['jobs'])) ))
  }

}
