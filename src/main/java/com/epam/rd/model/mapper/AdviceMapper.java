package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.AdviceDto;
import com.epam.rd.model.dto.AnswerDto;
import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.Answer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class AdviceMapper implements EntityMapper<AdviceDto, Advice> {

}