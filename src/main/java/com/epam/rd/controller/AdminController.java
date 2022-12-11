package com.epam.rd.controller;

import com.epam.rd.exceptions.ApiException;
import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.model.dto.PsychologistDto;
import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.*;
import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    private PsychologistService psychologistService;
    private CourseService courseService;
    private AnswerService answerService;
    private LinkService linkService;
    private QuestionService questionService;
    private TestService testService;
    private TestTypeService typeService;
    private UserService userService;


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

    @GetMapping(value = "/psycho/{id}")
    public ResponseEntity<PsychologistDto> getPsychologistById(@PathVariable Long id) {
        return ResponseEntity.ok(psychologistService.getPsychologistById(id));
    }


    /**
     * End point for Course entity  for Only ADMIN permission
     */

    @PostMapping("/course")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));

    }

    @PutMapping("/course")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(courseDto));
    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/course/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    /**
     * End point for Answer entity  for Only ADMIN permission
     */
    @PostMapping("/answer")
    public ResponseEntity<?> create(@RequestBody Answer entity) {
        try {
            return new ResponseEntity<>(answerService.create(entity), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }

    }

    @PutMapping("/answer")
    public ResponseEntity<Answer> update(@RequestBody Answer entity) {
        return new ResponseEntity<>(answerService.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<Answer> delete(@PathVariable Long id) {
        if (answerService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     *  End point for Link entity  for Only ADMIN permission
     */
    @PostMapping("/link")
    public ResponseEntity<?> create(@RequestBody Link entity) {
        try {
            return new ResponseEntity<>(linkService.create(entity), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }

    }

    @PutMapping("/link")
    public ResponseEntity<Link> update(@RequestBody Link entity) {
        return new ResponseEntity<>(linkService.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<Link> deleteLink(@PathVariable Long id) {
        if (linkService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * End point for Question entity  for Only ADMIN permission
     */
    @PostMapping("/question")
    public ResponseEntity<?> create(@RequestBody Question entity) {
        try {
            return new ResponseEntity<>(questionService.create(entity), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }

    }

    @PutMapping("/question")
    public ResponseEntity<Question> update(@RequestBody Question entity) {
        return new ResponseEntity<>(questionService.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        if (questionService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * End point for Test entity  for Only ADMIN permission
     */
    @PostMapping("/test")
    public ResponseEntity<?> create(@RequestBody Test entity) {
        try {
            return new ResponseEntity<>(testService.create(entity), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }

    }

    @PutMapping("/test")
    public ResponseEntity<Test> update(@RequestBody Test entity) {
        return new ResponseEntity<>(testService.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/test/{id}")
    public ResponseEntity<Test> deleteTest(@PathVariable Long id) {
        if (linkService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * End point for TestType entity  for Only ADMIN permission
     */
    @PostMapping("/type")
    public ResponseEntity<?> create(@RequestBody TestType entity) {
        try {
            return new ResponseEntity<>(typeService.create(entity), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }
    }

    @PutMapping("/type")
    public ResponseEntity<TestType> update(@RequestBody TestType entity) {
        return new ResponseEntity<>(typeService.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/type/{id}")
    public ResponseEntity<TestType> deleteType(@PathVariable Long id) {
        if (linkService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
    /**
     * User
     */

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

}
