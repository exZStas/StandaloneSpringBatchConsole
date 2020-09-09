import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  private REST_API_SERVER = 'http://localhost:8081';

  constructor(private http: HttpClient) { }
}
