package com.exzstas.spring.batch.adminconsole.job_execution.model;

import java.util.Date;

public record JobExecutionLite(Long executionId, String jobName, Date startDate, Date endDate, String exitCode) {
}
