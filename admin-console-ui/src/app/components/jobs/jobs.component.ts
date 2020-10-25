import { Component, OnInit } from '@angular/core';
import {JobExecutionsService} from '../../services/jobs-executions.service';
import {JobExecutionLite} from '../../model/job-execution-lite';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.scss']
})
export class JobsComponent implements OnInit {

  page = 1
  pageSize = 10
  startRow = 1
  jobExecutionsCounter

  jobExecutions: Observable<JobExecutionLite[]>

  constructor(private jobExecutionsService: JobExecutionsService) {
    this.refreshJobExecutions(this.startRow, this.pageSize)
    this.jobExecutionsService.getJobExecutionsCount().subscribe(next => {
      this.jobExecutionsCounter = next
    })
  }

  ngOnInit(): void {
  }

  refreshJobExecutions(page: number, pageSize: number) {
    if(page == 1) {
      this.startRow = 1
    } else {
      this.startRow = page * pageSize
    }

    this.jobExecutions = this.jobExecutionsService.getJobExecutionsLite(this.startRow, pageSize)
  }
}
