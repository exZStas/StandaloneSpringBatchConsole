export interface JobExecutionLite {
  executionId: number;
  jobName: string;
  startDate: Date;
  endDate: Date;
  exitCode: string;
}
