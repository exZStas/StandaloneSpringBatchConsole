import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {JobExecutionLite} from '../model/job-execution-lite';
import {JobExecution} from '../model/job-execution';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobExecutionsService {

  private REST_API_SERVER = 'http://localhost:8081/admin-console';

  constructor(private httpClient: HttpClient) { }

  public getJobExecutionsLite(startRow: number, maxRows: number): Observable<JobExecutionLite[]> {
    const params = new HttpParams()
      .set('startRow', `${startRow}`)
      .set('maxRows', `${maxRows}`);

    const jobExecutionsLiteUrl = this.REST_API_SERVER + '/job-executions/lite'; //todo refactor
    return this.httpClient.get<JobExecutionLite[]>(jobExecutionsLiteUrl, {params});
  }

  public getJobExecutionsCount(): Observable<Number> {
    const jobExecutionsCountUrl = this.REST_API_SERVER + '/job-executions/count'; //todo refactor
    return this.httpClient.get<Number>(jobExecutionsCountUrl);
  }

  public getJobExecutionById(jobExecutionId: number): Observable<JobExecution> {
    const jobExecutionUrl = this.REST_API_SERVER + `/job-executions/${jobExecutionId}`; //todo refactor
    return this.httpClient.get<JobExecution>(jobExecutionUrl);
  }
}
