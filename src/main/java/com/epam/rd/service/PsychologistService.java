package com.epam.rd.service;

import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.payload.response.AppointmentCalendarResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface PsychologistService {

    List<PsychologistDto> getAvailablePsychologists(LocalDateTime currentDateTime);

    AppointmentCalendarResponse getAppointmentCalendarByPsychologist(Long psychologistId, LocalDateTime currentDateTime);

    List<PsychologistDto> getAllPsychologists();

    PsychologistDto createPsychologist(PsychologistDto psychologistDto);

    void deletePsychologist(Long id);

    PsychologistDto getPsychologistById(Long id);

    PsychologistDto updatePsychologist(PsychologistDto psychologistDto);

    Page<PsychologistDto> getAllPsychologistsPaginated(int pageNum, int pageSize);

    Page<PsychologistDto> searchPsychologist(SearchRequest request);
}
