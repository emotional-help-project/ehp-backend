package com.epam.rd.service.impl;

import com.epam.rd.exceptions.PsychologistProcessingException;
import com.epam.rd.exceptions.UserProcessingException;
import com.epam.rd.model.entity.ClaimForAppointment;
import com.epam.rd.model.entity.Psychologist;
import com.epam.rd.model.entity.User;
import com.epam.rd.payload.request.ClaimForAppointmentRequest;
import com.epam.rd.payload.response.ClaimForAppointmentResponse;
import com.epam.rd.repository.ClaimForAppointmentRepository;
import com.epam.rd.repository.PsychologistRepository;
import com.epam.rd.repository.UserRepository;
import com.epam.rd.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final String CANNOT_FIND_USER_BY_ID = "Cannot find user with ID=";
    private static final String CANNOT_FIND_PSYCHOLOGIST_BY_ID = "Cannot find psychologist with ID=";

    private UserRepository userRepository;
    private PsychologistRepository psychologistRepository;
    private ClaimForAppointmentRepository claimForAppointmentRepository;

    @Transactional
    @Override
    public ClaimForAppointmentResponse saveClaimForAppointment(ClaimForAppointmentRequest claimRequest) {
        User user = userRepository.findById(claimRequest.getUserId())
                .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER_BY_ID + claimRequest.getUserId()));
        Psychologist psychologist = psychologistRepository.findById(claimRequest.getPsychologistId())
                .orElseThrow(() -> new PsychologistProcessingException(CANNOT_FIND_PSYCHOLOGIST_BY_ID + claimRequest.getPsychologistId()));
        ClaimForAppointment claim = new ClaimForAppointment()
                .setUser(user)
                .setPsychologist(psychologist)
                .setDesiredDate(claimRequest.getDesiredDate())
                .setUserPhone(claimRequest.getUserPhone());

        claimForAppointmentRepository.save(claim);

        return new ClaimForAppointmentResponse()
                .setPsychologistId(psychologist.getId())
                .setDesiredDate(claimRequest.getDesiredDate())
                .setUserPhone(claim.getUserPhone());
    }
}
