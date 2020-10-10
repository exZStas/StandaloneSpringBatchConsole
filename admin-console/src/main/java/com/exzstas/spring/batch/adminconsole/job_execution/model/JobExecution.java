package com.exzstas.spring.batch.adminconsole.job_execution.model;

import java.util.Date;

public record JobExecution(Long executionId, String jobName, Date startDate, Date endDate, String exitCode,
                           String status, String exitMessage, Date createDate, Date lastUpdated) {
}
