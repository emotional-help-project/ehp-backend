package com.epam.rd.service.impl;

import com.epam.rd.model.entity.Course;
import com.epam.rd.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Long> {

    public CourseServiceImpl(BaseRepository<Course> baseRepository) {
        super(baseRepository);
    }
}
