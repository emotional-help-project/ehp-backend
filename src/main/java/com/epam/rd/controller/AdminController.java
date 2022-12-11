package com.epam.rd.controller;

import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.service.AdminService;
import com.epam.rd.service.CourseService;
import com.epam.rd.service.PsychologistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    private PsychologistService psychologistService;
    private CourseService courseService;

    @PostMapping("/test")
    public ResponseEntity<?> addQuestionAnswersForTest(@RequestBody QuestionAnswerAdminRequest request) {
        return new ResponseEntity<>(adminService.addQuestionAnswersForTest(request), HttpStatus.CREATED);
    }

    @PostMapping("/advice")
    public ResponseEntity<?> addAdviceWithLinksForTest(@RequestBody AdviceAdminRequest request) {
        return new ResponseEntity<>(adminService.addAdviceWithLinksForTest(request), HttpStatus.CREATED);
    }

    /**
     * End point for Psychologist entity for Only ADMIN permission
     */

    @PostMapping("/psycho")
    public ResponseEntity<?> createPsychologist(@Valid @RequestBody PsychologistDto psychologistDto) {
        return ResponseEntity.ok(psychologistService.createPsychologist(psychologistDto));

    }

    @PutMapping("/psycho")
    public ResponseEntity<PsychologistDto> updatePsychologist(@Valid @RequestBody PsychologistDto psychologistDto) {
        return ResponseEntity.ok(psychologistService.updatePsychologist(psychologistDto));
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/psycho/{id}")
    public ResponseEntity<?> deletePsychologistById(@PathVariable Long id) {
        psychologistService.deletePsychologist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PsychologistDto> getPsychologistById(@PathVariable Long id) {
        return ResponseEntity.ok(psychologistService.getPsychologistById(id));
    }


    /**
     * End point for Course entity  for Only ADMIN permission
     */

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));

    }

    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(courseDto));
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

}
