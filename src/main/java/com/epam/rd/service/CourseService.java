package com.epam.rd.service;

import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.payload.response.BookedCourseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    void deleteCourse(Long id);

    CourseDto getCourseById(Long id);

    CourseDto updateCourse(CourseDto CourseDto);

    Page<CourseDto> getAllCoursesPaginated(int pageNum, int pageSize);

    List<CourseDto> getAllCourses();

    Page<CourseDto> searchCourse(SearchRequest request);

    BookedCourseResponse bookCourseForUser(Long id, Long userId);
}
