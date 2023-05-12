package com.epam.rd.service.impl;

import com.epam.rd.exceptions.CourseProcessingException;
import com.epam.rd.exceptions.PsychologistProcessingException;
import com.epam.rd.exceptions.PsychologistProcessingException;
import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.model.entity.*;
import com.epam.rd.model.enumerations.SlotType;
import com.epam.rd.model.mapper.PsychologistMapper;
import com.epam.rd.model.search.SearchSpecification;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.payload.response.AppointmentCalendarResponse;
import com.epam.rd.payload.response.AppointmentSchedule;
import com.epam.rd.repository.AppointmentRepository;
import com.epam.rd.repository.OccasionalPsyWorkTimeRepository;
import com.epam.rd.repository.PsychologistRepository;
import com.epam.rd.repository.StandardPsyWorkDaysRepository;
import com.epam.rd.service.PsychologistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private static final String CANNOT_FIND_PSYCHOLOGIST = "Cannot find psychologist with ID=";
    private static final String CAN_NOT_DELETE_PSYCHOLOGIST = "Can't delete psychologist with ID=";

    private PsychologistRepository psychologistRepository;
    private StandardPsyWorkDaysRepository standardPsyWorkDaysRepository;
    private OccasionalPsyWorkTimeRepository occasionalPsyWorkTimeRepository;
    private AppointmentRepository appointmentRepository;
    private PsychologistMapper psychologistMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PsychologistDto> getAvailablePsychologists(LocalDateTime currentDateTime) {
        return psychologistRepository.findAvailablePsychologists(currentDateTime)
                .stream().map(psychologistMapper::toDto).toList();
    }

    // TODO provide functionality when a user can get calendar, see available and booked slots of the psychologist
    //  and choose one of the free slots
    @Transactional
    @Override
    public AppointmentCalendarResponse getAppointmentCalendarByPsychologist(Long psychologistId, LocalDateTime currentDateTime) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PsychologistDto> getAllPsychologists() {
        return psychologistRepository.findAll().stream().map(psychologistMapper::toDto).toList();
    }

    @Transactional
    @Override
    public PsychologistDto createPsychologist(PsychologistDto psychologistDto) {
        return psychologistMapper.toDto(psychologistRepository.save(psychologistMapper.toEntity(psychologistDto)));
    }

    @Transactional
    @Override
    public void deletePsychologist(Long id) {
        try {
            psychologistRepository.deleteById(id);
        } catch (Exception exception) {
            throw new PsychologistProcessingException(CAN_NOT_DELETE_PSYCHOLOGIST + id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public PsychologistDto getPsychologistById(Long id) {
        Psychologist psychologist = psychologistRepository.findById(id)
                .orElseThrow(() -> new PsychologistProcessingException(CANNOT_FIND_PSYCHOLOGIST + id));
        return psychologistMapper.toDto(psychologist);
    }

    @Transactional
    @Override
    public PsychologistDto updatePsychologist(PsychologistDto psychologistDto) {
        Psychologist psychologistToBeUpdated = psychologistRepository.findById(psychologistDto.getId())
                .orElseThrow(() -> new PsychologistProcessingException(CANNOT_FIND_PSYCHOLOGIST));

        if (psychologistDto.getAge() >= 18) {
            psychologistToBeUpdated.setAge(psychologistDto.getAge());
        }

        if (psychologistDto.getEducation() != null) {
            psychologistToBeUpdated.setEducation(psychologistDto.getEducation());
        }

        if (psychologistDto.getEmail() != null) {
            psychologistToBeUpdated.setEmail(psychologistDto.getEmail());
        }

        if (psychologistDto.getFirstName() != null) {
            psychologistToBeUpdated.setFirstName(psychologistDto.getFirstName());
        }

        if (psychologistDto.getLastName() != null) {
            psychologistToBeUpdated.setLastName(psychologistDto.getLastName());
        }

        if (psychologistDto.getAvatar() != null) {
            psychologistToBeUpdated.setAvatar(psychologistToBeUpdated.getAvatar());
        }

        if (psychologistDto.getGender() != null) {
            psychologistToBeUpdated.setGender(psychologistDto.getGender());
        }

        if (psychologistDto.getLicense() != null) {
            psychologistToBeUpdated.setLicense(psychologistDto.getLicense());
        }

        if (psychologistDto.getQualification() != null) {
            psychologistToBeUpdated.setQualification(psychologistDto.getQualification());
        }

        return psychologistMapper.toDto(psychologistRepository.save(psychologistToBeUpdated));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PsychologistDto> getAllPsychologistsPaginated(int pageNum, int pageSize) {
        Pageable pageable = createPageRequest(pageNum, pageSize);
        return psychologistRepository.findAll(pageable).map(psychologistMapper::toDto);
    }

    @Transactional
    @Override
    public Page<PsychologistDto> searchPsychologist(SearchRequest request) {
        SearchSpecification<Psychologist> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return psychologistRepository.findAll(specification, pageable).map(psychologistMapper::toDto);
    }

    private PageRequest createPageRequest(int pageNum, int pageSize) {
        return PageRequest.of(pageNum - 1, pageSize, Sort.by("id").descending());
    }

}
