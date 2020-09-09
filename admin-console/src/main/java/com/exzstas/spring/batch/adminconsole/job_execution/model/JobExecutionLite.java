package com.exzstas.spring.batch.adminconsole.job_execution.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record JobExecutionLite(Long executionId, String jobName, Date startDate, Date endDate, String exitCode) {
}
