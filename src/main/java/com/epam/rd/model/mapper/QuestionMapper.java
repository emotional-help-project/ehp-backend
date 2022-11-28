package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.QuestionDto;
import com.epam.rd.model.entity.Question;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class QuestionMapper implements EntityMapper<QuestionDto, Question> {



}


