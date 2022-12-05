package com.epam.rd.controller;

import com.epam.rd.payload.request.ClaimForAppointmentRequest;
import com.epam.rd.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
@AllArgsConstructor
@Validated
public class AppointmentController {

    private AppointmentService appointmentService;

    @PostMapping("/appoint")
    public ResponseEntity<?> makeClaimForAppointment(@RequestBody @Valid ClaimForAppointmentRequest claimForAppointmentRequest) {
        return ResponseEntity.ok(appointmentService.saveClaimForAppointment(claimForAppointmentRequest));
    }

}
