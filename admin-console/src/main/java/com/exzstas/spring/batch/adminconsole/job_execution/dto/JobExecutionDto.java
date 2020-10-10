package com.exzstas.spring.batch.adminconsole.job_execution.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JobExecutionDto {

    private Long executionId;
    private Date startDate;
    private Date endDate;
    private String status;
    private String exitCode;
    private String exitMessage;
    private String jobName;
    private Date createDate;
    private Date lastUpdated;
}
