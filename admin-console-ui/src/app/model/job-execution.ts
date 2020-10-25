export interface JobExecution {
  executionId: number;
  jobName: string;
  startDate: Date;
  endDate: Date;
  exitCode: string;
  exitMessage: string;
  status: string;
  createDate: Date;
  lastUpdated: Date;
}
