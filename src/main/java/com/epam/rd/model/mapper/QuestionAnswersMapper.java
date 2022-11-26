package com.epam.rd.model.mapper;

import com.epam.rd.model.entity.UserAnswers;
import com.epam.rd.payload.request.QuestionAnswerRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class QuestionAnswersMapper implements EntityMapper<QuestionAnswerRequest, UserAnswers> {



}


