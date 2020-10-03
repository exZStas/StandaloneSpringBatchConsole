package com.exzstas.spring.batch.adminconsole.job_execution.dto;

import com.exzstas.spring.batch.adminconsole.job_execution.model.JobExecutionLite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobExecutionMapper {

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    JobExecutionLiteDto entityToDto(JobExecutionLite entity);

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    List<JobExecutionLiteDto> entitiesToDto(List<JobExecutionLite> entities);
}
