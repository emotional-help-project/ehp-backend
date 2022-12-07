package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.model.entity.Course;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class CourseMapper implements EntityMapper<CourseDto, Course> {

}

