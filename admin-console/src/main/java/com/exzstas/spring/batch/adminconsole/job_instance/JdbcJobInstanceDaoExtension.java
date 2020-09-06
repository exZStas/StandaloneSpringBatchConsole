package com.exzstas.spring.batch.adminconsole.job_instance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.dao.JdbcJobExecutionDao;
import org.springframework.batch.core.repository.dao.JdbcJobInstanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class JdbcJobInstanceDaoExtension extends JdbcJobInstanceDao {

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

    private final class JobInstanceRowMapper implements RowMapper<JobInstance> {

        public JobInstanceRowMapper() {
        }

        @Override
        public JobInstance mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobInstance jobInstance = new JobInstance(rs.getLong(1), rs.getString(2));
            // should always be at version=0 because they never get updated
            jobInstance.incrementVersion();
            return jobInstance;
        }
    }

}
