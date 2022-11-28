package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.SessionDto;
import com.epam.rd.model.entity.Session;
import com.epam.rd.model.entity.TestType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class SessionMapper implements EntityMapper<SessionDto, Session> {

}