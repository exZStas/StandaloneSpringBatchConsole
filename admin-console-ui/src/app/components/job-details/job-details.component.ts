import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {JobExecution} from '../../model/job-execution';
import {Observable} from 'rxjs';
import {JobExecutionsService} from '../../services/jobs-executions.service';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  jobExecution: any

  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.jobExecution = this.activatedRoute.snapshot.data['jobDetails']
  }

  goBack() {
    this.router.navigate(['jobs'])
  }


}
