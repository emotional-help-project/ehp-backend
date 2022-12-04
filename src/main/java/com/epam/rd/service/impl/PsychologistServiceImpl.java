package com.epam.rd.service.impl;

import com.epam.rd.exceptions.PsychologistProcessingException;
import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.model.entity.Appointment;
import com.epam.rd.model.entity.OccasionalPsyWorkTime;
import com.epam.rd.model.entity.Psychologist;
import com.epam.rd.model.entity.StandardPsyWorkDays;
import com.epam.rd.model.enumerations.SlotType;
import com.epam.rd.model.mapper.PsychologistMapper;
import com.epam.rd.payload.response.AppointmentCalendarResponse;
import com.epam.rd.payload.response.AppointmentSchedule;
import com.epam.rd.repository.AppointmentRepository;
import com.epam.rd.repository.OccasionalPsyWorkTimeRepository;
import com.epam.rd.repository.PsychologistRepository;
import com.epam.rd.repository.StandardPsyWorkDaysRepository;
import com.epam.rd.service.PsychologistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private static final String CANNOT_FIND_PSYCHOLOGIST = "Cannot find psychologist with ID=";

    private PsychologistRepository psychologistRepository;
    private StandardPsyWorkDaysRepository standardPsyWorkDaysRepository;
    private OccasionalPsyWorkTimeRepository occasionalPsyWorkTimeRepository;
    private AppointmentRepository appointmentRepository;
    private PsychologistMapper psychologistMapper;

    @Transactional
    @Override
    public List<PsychologistDto> getAvailablePsychologists(LocalDateTime currentDateTime) {
        return psychologistRepository.findAvailablePsychologists(currentDateTime)
                .stream().map(psychologistMapper::toDto).toList();
    }

    @Transactional
    @Override
    public AppointmentCalendarResponse getAppointmentCalendarByPsychologist(Long psychologistId, LocalDateTime currentDateTime) {
        Psychologist psychologist = psychologistRepository.findById(psychologistId)
                .orElseThrow(() -> new PsychologistProcessingException(CANNOT_FIND_PSYCHOLOGIST));
        List<StandardPsyWorkDays> standardPsyWorkDays = standardPsyWorkDaysRepository.findAllByPsychologist(psychologist);
        List<OccasionalPsyWorkTime> occasionalPsyWorkTimes = occasionalPsyWorkTimeRepository.findAllByPsychologist(psychologist);
        List<Appointment> bookedAppointmentsByPsychologist = appointmentRepository.findBookedAppointmentsByPsychologist(psychologist, currentDateTime);

        List<AppointmentSchedule> appointmentSchedule = new ArrayList<>();
        

        return null;
    }


}
