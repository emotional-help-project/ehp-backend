package com.epam.rd.controller;

import com.epam.rd.model.entity.Course;
import com.epam.rd.service.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/courses")
public class CourseController extends BaseController<Course> {

    public CourseController(CommonService<Course, Long> commonService) {
        super(commonService);
    }
}
