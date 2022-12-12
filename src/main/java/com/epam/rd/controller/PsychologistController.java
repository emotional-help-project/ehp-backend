package com.epam.rd.controller;

import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.service.PsychologistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/psychologists")
@AllArgsConstructor
public class PsychologistController {

    private final PsychologistService psychologistService;

    @GetMapping("/available")
    public ResponseEntity<?> getPsychologistsForAppointment(@RequestParam LocalDateTime currentDateTime) {
        return ResponseEntity.ok(psychologistService.getAvailablePsychologists(currentDateTime));
    }

    @GetMapping
    public ResponseEntity<?> getAllPsychologists() {
        return ResponseEntity.ok(psychologistService.getAllPsychologists());
    }

    @GetMapping("/psy/{psychologistId}")
    public ResponseEntity<?> getAppointmentCalendar(@PathVariable Long psychologistId,
                                                    @RequestParam LocalDateTime currentDateTime) {
        return ResponseEntity.ok(psychologistService.getAppointmentCalendarByPsychologist(psychologistId, currentDateTime));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PsychologistDto> getPsychologistById(@PathVariable Long id) {
        return ResponseEntity.ok(psychologistService.getPsychologistById(id));
    }

    @GetMapping("/page")
    public Page<PsychologistDto> getAllPsychologistsPaginated(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                              @RequestParam(required = false, defaultValue = "5") int pageSize) {
        return psychologistService.getAllPsychologistsPaginated(pageNum, pageSize);
    }

    @PostMapping(value = "/search")
    public Page<PsychologistDto> search(@RequestBody SearchRequest request) {
        return psychologistService.searchPsychologist(request);
    }


}