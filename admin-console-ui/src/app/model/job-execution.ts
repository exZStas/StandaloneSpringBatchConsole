export interface JobExecution {
  executionId: number;
  jobName: string;
  startDate: Date;
  endDate: Date;
  exitCode: string;
}
