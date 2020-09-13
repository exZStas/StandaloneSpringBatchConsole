import { Component, OnInit } from '@angular/core';
import {JobsService} from '../../services/jobs.service';
import {JobExecution} from '../../model/job-execution';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {

  jobExecutions: Observable<JobExecution[]> = this.jobsService.getJobExportsLite(1,2)

  constructor(private  jobsService: JobsService) { }

  ngOnInit(): void {
  }

}
