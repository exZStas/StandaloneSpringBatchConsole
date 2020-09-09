package com.exzstas.spring.batch.adminconsole.job_execution;

import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecutionLite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.repository.dao.JdbcJobExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JdbcJobExecutionDaoExtension extends JdbcJobExecutionDao {

    private JdbcOperations jdbcTemplate;

    private static final String GET_JOB_EXECUTIONS = "SELECT JOB_EXECUTION_ID, START_TIME, END_TIME, STATUS, EXIT_CODE," +
            " EXIT_MESSAGE, CREATE_TIME, LAST_UPDATED, %PREFIX%JOB_EXECUTION.VERSION, JOB_CONFIGURATION_LOCATION" +
            " from %PREFIX%JOB_EXECUTION" +
            " join batch_job_instance on %PREFIX%JOB_EXECUTION.job_instance_id = batch_job_instance.job_instance_id " +
            " where batch_job_instance.job_name = ?";

    private static final String GET_JOB_EXECUTIONS_LITE = "SELECT JOB_EXECUTION_ID, JOB_NAME, START_TIME, END_TIME, EXIT_CODE " +
            "from BATCH_JOB_EXECUTION " +
            "join batch_job_instance on BATCH_JOB_EXECUTION.job_instance_id = batch_job_instance.job_instance_id";


    public List<JobExecution> getJobExecutionsByJobName(String jobName) {
        return jdbcTemplate.query(getQuery(GET_JOB_EXECUTIONS),
                new JobExecutionRowMapper(), jobName);
    }

    public List<JobExecutionLite> getJobExecutionsLite() {
        return jdbcTemplate.query(getQuery(GET_JOB_EXECUTIONS_LITE),
                new JobExecutionLiteRowMapper());
    }

    @Override
    public void afterPropertiesSet() {
    }

    @Override
    @Autowired
    public void setJdbcTemplate(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected JdbcOperations getJdbcTemplate() {
        return jdbcTemplate;
    }

    private final class JobExecutionRowMapper implements RowMapper<JobExecution> {

        public JobExecutionRowMapper() {}

        @Override
        public JobExecution mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong(1);

            String jobConfigurationLocation = rs.getString(10);
            JobParameters jobParameters = getJobParameters(id);

            JobExecution jobExecution = new JobExecution(id, jobParameters, jobConfigurationLocation);

            jobExecution.setStartTime(rs.getTimestamp(2));
            jobExecution.setEndTime(rs.getTimestamp(3));
            jobExecution.setStatus(BatchStatus.valueOf(rs.getString(4)));
            jobExecution.setExitStatus(new ExitStatus(rs.getString(5), rs.getString(6)));
            jobExecution.setCreateTime(rs.getTimestamp(7));
            jobExecution.setLastUpdated(rs.getTimestamp(8));
            jobExecution.setVersion(rs.getInt(9));
            return jobExecution;
        }

    }

    private final class JobExecutionLiteRowMapper implements RowMapper<JobExecutionLite> {

        public JobExecutionLiteRowMapper() {}

        @Override
        public JobExecutionLite mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong(1);
            String jobName = rs.getString(2);
            Date startDate = rs.getDate(3);
            Date endDate = rs.getDate(4);
            String exitCode = rs.getString(5);

            return new JobExecutionLite(id, jobName, startDate, endDate, exitCode);
        }

    }

}
