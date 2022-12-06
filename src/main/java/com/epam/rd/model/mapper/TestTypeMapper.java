package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.TestTypeDto;
import com.epam.rd.model.entity.TestType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
@Mapper(componentModel = "spring")
@Component
public abstract class TestTypeMapper implements EntityMapper<TestTypeDto, TestType>  {
}
