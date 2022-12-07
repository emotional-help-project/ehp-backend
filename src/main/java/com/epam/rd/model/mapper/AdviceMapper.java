package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.AdviceDto;
import com.epam.rd.model.entity.Advice;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class AdviceMapper implements EntityMapper<AdviceDto, Advice> {

}
