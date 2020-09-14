import { Component, OnInit } from '@angular/core';
import {JobsService} from '../../services/jobs.service';
import {JobExecution} from '../../model/job-execution';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {

  startRow = 1;
  maxRows = 10;

  jobExecutions: Observable<JobExecution[]>

  constructor(private  jobsService: JobsService) {
    this.refreshJobExecutions(this.startRow, this.maxRows)
  }

  ngOnInit(): void {
  }

  refreshJobExecutions(startRow: number, maxRows: number) {
    this.jobExecutions = this.jobsService.getJobExportsLite(startRow,maxRows)
  }
}
