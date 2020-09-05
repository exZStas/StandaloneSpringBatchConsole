package com.exzstas.spring.batch.adminconsole.batch_execution;

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

@Slf4j
@Component
public class JdbcJobExecutionDaoExtension extends JdbcJobExecutionDao {

    private JdbcOperations jdbcTemplate;

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

}
