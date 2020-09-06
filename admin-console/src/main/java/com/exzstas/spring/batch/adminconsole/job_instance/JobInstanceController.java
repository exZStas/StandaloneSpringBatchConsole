package com.exzstas.spring.batch.adminconsole.job_instance;

import com.exzstas.spring.batch.adminconsole.job_execution.JdbcJobExecutionDaoExtension;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.dao.JdbcJobInstanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("instances")
public class JobInstanceController {

    @Autowired
    private JdbcJobInstanceDao jdbcJobInstanceDao;

    @GetMapping("/names")
    public ResponseEntity getJobNames() {

        List<String> jobNames = jdbcJobInstanceDao.getJobNames();

        return new ResponseEntity(jobNames, HttpStatus.OK);
    }
}
