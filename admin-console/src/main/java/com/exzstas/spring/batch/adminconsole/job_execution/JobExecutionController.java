package com.exzstas.spring.batch.adminconsole.job_execution;

import com.exzstas.spring.batch.adminconsole.job_execution.dto.JobExecutionDto;
import com.exzstas.spring.batch.adminconsole.job_execution.dto.JobExecutionLiteDto;
import com.exzstas.spring.batch.adminconsole.job_execution.dto.JobExecutionMapper;
import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecution;
import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecutionLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job-executions")
public class JobExecutionController {

    @Autowired
    private JdbcJobExecutionDaoExtension jdbcJobExecutionDaoExtension;

    @Autowired
    private JobExecutionMapper jobExecutionMapper;

    @GetMapping(path = "{jobId}")
    public ResponseEntity<JobExecutionDto> getJobExecution(@PathVariable("jobId") Long jobId) {
        JobExecution jobExecution = jdbcJobExecutionDaoExtension.getJobExecutionById(jobId);
        JobExecutionDto jobExecutionDto = jobExecutionMapper.jobExecutionToDto(jobExecution);

        return new ResponseEntity<>(jobExecutionDto, HttpStatus.OK);
    }

    @GetMapping("/lite")
    public ResponseEntity<List<JobExecutionLiteDto>> getJobExecutionsLite(@RequestParam(value = "startRow") int startRow,
                                               @RequestParam(value = "maxRows") int maxRow) {
        List<JobExecutionLite> jobExecutionsLite = jdbcJobExecutionDaoExtension.getJobExecutionsLite(startRow, maxRow);
        List<JobExecutionLiteDto> jobExecutionLiteDtos = jobExecutionMapper.jobExecutionsLiteToDto(jobExecutionsLite);

        return new ResponseEntity<>(jobExecutionLiteDtos, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getJobExecutionsLiteCount() {
        Long jobExecutionsCount = jdbcJobExecutionDaoExtension.getJobExecutionsCount();

        return new ResponseEntity<>(jobExecutionsCount, HttpStatus.OK);
    }
}
