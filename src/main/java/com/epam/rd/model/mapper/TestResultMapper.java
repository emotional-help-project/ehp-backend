package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.TestResultDto;
import com.epam.rd.model.entity.TestResult;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class TestResultMapper implements EntityMapper<TestResultDto, TestResult> {
}
