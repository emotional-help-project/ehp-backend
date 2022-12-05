package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Appointment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class StandardTImeAndBookedAppDto {

    LocalDate standardTimeStart;
    LocalDate standardTimeEnd;
    List<Appointment> bookedAppointments;
}
