package com.epam.rd.service;

import com.epam.rd.payload.request.ClaimForAppointmentRequest;
import com.epam.rd.payload.response.ClaimForAppointmentResponse;

public interface AppointmentService {
    ClaimForAppointmentResponse saveClaimForAppointment(ClaimForAppointmentRequest claimRequest);
}
