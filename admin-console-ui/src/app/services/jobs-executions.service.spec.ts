import { TestBed } from '@angular/core/testing';

import { JobExecutionsService } from './jobs.service';

describe('JobsService', () => {
  let service: JobExecutionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobExecutionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
