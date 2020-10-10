package com.exzstas.spring.batch.adminconsole.job_execution.dto;

import lombok.Data;


@Data
public class JobExecutionLiteDto {

    private Long executionId;
    private String jobName;
    private String startDate;
    private String endDate;
    private String exitCode;
}
