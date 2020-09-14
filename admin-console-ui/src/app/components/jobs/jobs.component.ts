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

  page = 1
  pageSize = 10
  startRow = 1
  jobExecutionsCounter

  jobExecutions: Observable<JobExecution[]>

  constructor(private  jobsService: JobsService) {
    this.refreshJobExecutions(this.startRow, this.pageSize)
    this.jobsService.getJobExportsCount().subscribe(next => {
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

    this.jobExecutions = this.jobsService.getJobExportsLite(this.startRow, pageSize)
  }
}
