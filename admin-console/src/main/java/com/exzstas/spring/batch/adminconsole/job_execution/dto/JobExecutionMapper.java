package com.exzstas.spring.batch.adminconsole.job_execution.dto;

import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecution;
import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecutionLite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobExecutionMapper {

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    List<JobExecutionLiteDto> jobExecutionsLiteToDto(List<JobExecutionLite> entities);

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "createDate", target = "createDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "lastUpdated", target = "lastUpdated", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    JobExecutionDto jobExecutionToDto(JobExecution entity);
}
