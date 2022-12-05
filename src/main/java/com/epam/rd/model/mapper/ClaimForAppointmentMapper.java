package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.ClaimForAppointmentDto;
import com.epam.rd.model.entity.ClaimForAppointment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class ClaimForAppointmentMapper implements EntityMapper<ClaimForAppointmentDto, ClaimForAppointment> {
}
