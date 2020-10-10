package com.exzstas.spring.batch.adminconsole.job_execution;

import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecution;
import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecutionLite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
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

    private static final String GET_JOB_EXECUTIONS_BY_ID = """
             select JOB_EXECUTION_ID, START_TIME, END_TIME, STATUS, EXIT_CODE,
             EXIT_MESSAGE, CREATE_TIME, LAST_UPDATED, JOB_NAME
             from %PREFIX%JOB_EXECUTION
             join batch_job_instance on %PREFIX%JOB_EXECUTION.job_instance_id = batch_job_instance.job_instance_id 
             where %PREFIX%JOB_EXECUTION.job_execution_id = ?
             """;

    private static final String GET_JOB_EXECUTIONS_LITE = """
            select JOB_EXECUTION_ID, JOB_NAME, START_TIME, END_TIME, EXIT_CODE 
            from BATCH_JOB_EXECUTION 
            join batch_job_instance on BATCH_JOB_EXECUTION.job_instance_id = batch_job_instance.job_instance_id
            order by JOB_EXECUTION_ID desc
            offset ? rows fetch next ? rows only
            """;

    private static final String GET_JOB_EXECUTIONS_COUNT = """
            select count(*) from BATCH_JOB_EXECUTION
            """;


    public JobExecution getJobExecutionById(Long jobId) {
        return jdbcTemplate.queryForObject(getQuery(GET_JOB_EXECUTIONS_BY_ID),
                new JobExecutionRowMapper(), jobId);
    }

    public List<JobExecutionLite> getJobExecutionsLite(int startRow, int maxRow) {
        return jdbcTemplate.query(getQuery(GET_JOB_EXECUTIONS_LITE),
                new JobExecutionLiteRowMapper(), startRow, maxRow);
    }

    public Long getJobExecutionsCount() {
        return jdbcTemplate.queryForObject(getQuery(GET_JOB_EXECUTIONS_COUNT), Long.class);
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

//    private final class JobExecutionRowMapper implements RowMapper<JobExecution> {
//
//        public JobExecutionRowMapper() {}
//
//        @Override
//        public JobExecution mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Long id = rs.getLong(1);
//
//            String jobConfigurationLocation = rs.getString(10);
//            JobParameters jobParameters = getJobParameters(id);
//
//            JobExecution jobExecution = new JobExecution(id, jobParameters, jobConfigurationLocation);
//
//            jobExecution.setStartTime(rs.getTimestamp(2));
//            jobExecution.setEndTime(rs.getTimestamp(3));
//            jobExecution.setStatus(BatchStatus.valueOf(rs.getString(4)));
//            jobExecution.setExitStatus(new ExitStatus(rs.getString(5), rs.getString(6)));
//            jobExecution.setCreateTime(rs.getTimestamp(7));
//            jobExecution.setLastUpdated(rs.getTimestamp(8));
//            return jobExecution;
//        }
//
//    }

    private final class JobExecutionRowMapper implements RowMapper<JobExecution> {

        public JobExecutionRowMapper() {}

        @Override
        public JobExecution mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong(1);
            Date startDate = rs.getTimestamp(2);
            Date endDate = rs.getTimestamp(3);
            String status = rs.getString(4);
            String exitCode = rs.getString(5);
            String exitMessage = rs.getString(6);
            Date createDate = rs.getTimestamp(7);
            Date lastUpdated = rs.getTimestamp(8);
            String jobName = rs.getString(9);

            return new JobExecution(id, jobName, startDate, endDate, exitCode, status, exitMessage, createDate, lastUpdated);
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
