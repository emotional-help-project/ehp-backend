package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.model.entity.Psychologist;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class PsychologistMapper implements EntityMapper<PsychologistDto, Psychologist> {
}
