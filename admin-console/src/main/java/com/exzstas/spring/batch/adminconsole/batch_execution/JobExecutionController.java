package com.exzstas.spring.batch.adminconsole.batch_execution;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobExecutionController {

    @Autowired
    private JdbcJobExecutionDaoExtension jdbcJobExecutionDaoExtension;

    @GetMapping("/executions")
    public ResponseEntity getJobExecutions() {

        JobExecution jobExecution = jdbcJobExecutionDaoExtension.getJobExecution(61L);

        return new ResponseEntity(jobExecution, HttpStatus.OK);
    }
}
