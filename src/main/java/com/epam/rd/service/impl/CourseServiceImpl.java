package com.epam.rd.service.impl;

import com.epam.rd.exceptions.CourseProcessingException;
import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.model.entity.Course;
import com.epam.rd.model.mapper.CourseMapper;
import com.epam.rd.model.search.SearchSpecification;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.repository.CourseRepository;
import com.epam.rd.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private static final String CANNOT_FIND_COURSE = "Cannot find course with ID=";
    private CourseRepository courseRepository;
    private CourseMapper courseMapper;

    @Transactional
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(courseDto)));
    }

    @Transactional
    @Override
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseProcessingException(CANNOT_FIND_COURSE + id));
        return courseMapper.toDto(course);
    }

    @Transactional
    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        Course courseToBeUpdated = courseRepository.findById(courseDto.getId())
                .orElseThrow(() -> new CourseProcessingException(CANNOT_FIND_COURSE));


        if (courseDto.getDescription() != null) {
            courseToBeUpdated.setDescription(courseToBeUpdated.getDescription());
        }

        if (courseDto.getPrice() != null && courseDto.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            courseToBeUpdated.setPrice(courseDto.getPrice());
        }

        if (courseDto.getImageUrl() != null) {
            courseToBeUpdated.setImageUrl(courseDto.getImageUrl());
        }

        return courseMapper.toDto(courseRepository.save(courseToBeUpdated));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourseDto> getAllCoursesPaginated(int pageNum, int pageSize) {
        Pageable pageable = createPageRequest(pageNum, pageSize);
        return courseRepository.findAll(pageable).map(courseMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public Page<CourseDto> searchCourse(SearchRequest request) {
        SearchSpecification<Course> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return courseRepository.findAll(specification, pageable).map(courseMapper::toDto);
    }

    private PageRequest createPageRequest(int pageNum, int pageSize) {
        return PageRequest.of(pageNum - 1, pageSize, Sort.by("id").descending());
    }

}
