package com.epam.rd.controller;

import com.epam.rd.service.PsychologistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/psychologists")
@AllArgsConstructor
public class PsychologistController {

    private final PsychologistService psychologistService;

    @GetMapping()
    public ResponseEntity<?> getPsychologistsForAppointment(@RequestParam LocalDateTime currentDateTime) {
        return ResponseEntity.ok(psychologistService.getAvailablePsychologists(currentDateTime));
    }

    @GetMapping("/psy/{psychologistId}")
    public ResponseEntity<?> getAppointmentCalendar(@PathVariable Long psychologistId,
                                                    @RequestParam LocalDateTime currentDateTime) {
        return ResponseEntity.ok(psychologistService.getAppointmentCalendarByPsychologist(psychologistId, currentDateTime));
    }


}
