package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.UserAnswersDto;
import com.epam.rd.model.entity.UserAnswers;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class UserAnswersMapper implements EntityMapper<UserAnswersDto, UserAnswers> {

}

