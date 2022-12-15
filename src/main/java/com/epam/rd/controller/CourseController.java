package com.epam.rd.controller;

import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(courseDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public Page<CourseDto> getAllCoursesPaginated(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                  @RequestParam(required = false, defaultValue = "5") int pageSize) {
        return courseService.getAllCoursesPaginated(pageNum, pageSize);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping(value = "/search")
    public Page<CourseDto> search(@RequestBody SearchRequest request) {
        return courseService.searchCourse(request);
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<?> bookCourse(@PathVariable Long id,
                                        @RequestParam Long userId) {
        return ResponseEntity.ok(courseService.bookCourseForUser(id, userId));
    }
}
