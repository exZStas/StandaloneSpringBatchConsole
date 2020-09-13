package com.exzstas.spring.batch.adminconsole.job_execution;

import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecutionLite;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("executions")
public class JobExecutionController {

    @Autowired
    private JdbcJobExecutionDaoExtension jdbcJobExecutionDaoExtension;

    @GetMapping
    public ResponseEntity getJobExecutions(@RequestParam("jobName") String jobName) {

        List<JobExecution> jobExecution = jdbcJobExecutionDaoExtension.getJobExecutionsByJobName(jobName);

        return new ResponseEntity(jobExecution, HttpStatus.OK);
    }

    @GetMapping("/lite")
    public ResponseEntity getJobExecutionsLite(@RequestParam(value = "startRow") int startRow,
                                               @RequestParam(value = "maxRows") int maxRow) {

        List<JobExecutionLite> jobExecutionsLite = jdbcJobExecutionDaoExtension.getJobExecutionsLite(startRow, maxRow);

        return new ResponseEntity(jobExecutionsLite, HttpStatus.OK);
    }
}
