package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.TestDto;
import com.epam.rd.model.entity.Test;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class TestMapper implements EntityMapper<TestDto, Test> {

}