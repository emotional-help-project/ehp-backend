package com.epam.rd.service;

import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.payload.response.AppointmentCalendarResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PsychologistService {

    List<PsychologistDto> getAvailablePsychologists(LocalDateTime currentDateTime);

    AppointmentCalendarResponse getAppointmentCalendarByPsychologist(Long psychologistId, LocalDateTime currentDateTime);

    List<PsychologistDto> getAllPsychologists();
}
