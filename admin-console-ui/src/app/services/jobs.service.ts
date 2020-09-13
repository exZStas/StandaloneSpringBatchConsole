import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {JobExecution} from '../model/job-execution';
import {catchError, map, retry, tap} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  private REST_API_SERVER = 'http://localhost:8081/admin-console';

  constructor(private httpClient: HttpClient) { }

  public getJobExportsLite(startRow: number, maxRows: number): Observable<JobExecution[]> {
    const params = new HttpParams()
      .set('startRow', `${startRow}`)
      .set('maxRows', `${maxRows}`);

    const jobExportsLiteUrl = this.REST_API_SERVER + '/executions/lite';
    return this.httpClient.get<JobExecution[]>(jobExportsLiteUrl, {params});
  }
}
