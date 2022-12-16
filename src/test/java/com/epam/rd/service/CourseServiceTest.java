package com.epam.rd.service;

import com.epam.rd.UnitTestExpectedDtoSupplier;
import com.epam.rd.exceptions.CourseProcessingException;
import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.model.entity.Course;
import com.epam.rd.model.mapper.CourseMapper;
import com.epam.rd.model.mapper.CourseMapperImpl;
import com.epam.rd.repository.CourseRepository;
import com.epam.rd.repository.UserCourseRepository;
import com.epam.rd.repository.UserRepository;
import com.epam.rd.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    private static final String CANNOT_FIND_COURSE = "Cannot find course with ID=";
    private static final String CAN_NOT_DELETE_COURSE = "Can't delete course with ID=";
    private static final Long NOT_EXIST_ID = -1L;
    private static final Long DEFAULT_COURSE_ID = 123L;

    @Mock
    private CourseRepository courseRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private UserCourseRepository userCourseRepositoryMock;
    private CourseMapper courseMapper;
    private CourseService courseService;
    private CourseDto courseDto;

    @BeforeEach
    public void setUp() {
        courseMapper = new CourseMapperImpl();
        courseService = new CourseServiceImpl(courseRepositoryMock, userRepositoryMock, userCourseRepositoryMock, courseMapper);
        courseDto = UnitTestExpectedDtoSupplier.createCourseDto();
    }

    @Test
    void testGetAllCourses_whenCalled_callsRepo() {

        courseService.getAllCourses();
        verify(courseRepositoryMock, times(1)).findAll();
    }

    @Test
    void testCreateCourse_whenCalled_callsRepo() {

        courseService.createCourse(new CourseDto());
        verify(courseRepositoryMock, times(1)).save(any(Course.class));
    }

    @Test
    void testGetCourseById_whenCalled_RepoCalled() {
        Course course = courseMapper.toEntity(courseDto);

        when(courseRepositoryMock.findById(any())).thenReturn(Optional.of(course));

        courseService.getCourseById(DEFAULT_COURSE_ID);
        verify(courseRepositoryMock, times(1)).findById(DEFAULT_COURSE_ID);
    }

    @Test
    void testGetCourseById_whenRepoThrows_throwsException() {

        when(courseRepositoryMock.findById(DEFAULT_COURSE_ID)).thenThrow(
                new CourseProcessingException(CANNOT_FIND_COURSE + DEFAULT_COURSE_ID));

        assertThrowsExactly(CourseProcessingException.class, () -> courseService.getCourseById(DEFAULT_COURSE_ID));
    }

    @Test
    void testGetCourseById_whenThrowsNotFoundException_ShouldShowExceptionMessage() {
        String messageNotToGet = "aaaaa";
        long courseId = 1125L;

        when(courseRepositoryMock.findById(courseId)).thenReturn(Optional.empty());

        CourseProcessingException exception = assertThrows(CourseProcessingException.class,
                () -> courseService.getCourseById(courseId));
        assertEquals(CANNOT_FIND_COURSE + courseId, exception.getMessage());
        assertNotEquals(messageNotToGet, exception.getMessage());

    }

    @Test
    void testGetCourseById_whenCalled_returnsCorrectCourse() {
        long courseId = 123L;

        when(courseRepositoryMock.findById(courseId)).thenReturn(
                Optional.of(courseMapper.toEntity(courseDto)));

        CourseDto result = courseService.getCourseById(courseId);

        assertEquals(courseDto.getId(), result.getId());
        assertEquals(courseDto.getTitle(), result.getTitle());
        assertEquals(courseDto.getDescription(), result.getDescription());
        assertEquals(courseDto.getPrice(), result.getPrice());
        assertEquals(courseDto.getImageUrl(), result.getImageUrl());
        assertEquals(courseDto.getDateCreated(), result.getDateCreated());
    }

    @Test
    void testDeleteCourse_WhenCourseCanBeDeleted_RepoCalled() {
        courseService.deleteCourse(DEFAULT_COURSE_ID);
        verify(courseRepositoryMock, times(1)).deleteById(DEFAULT_COURSE_ID);
    }

    @Test
    void testDeleteCourse_WhenCourseNotFound_ShouldThrowException() {
        doThrow(CourseProcessingException.class)
                .when(courseRepositoryMock)
                .deleteById(NOT_EXIST_ID);

        assertThrows(CourseProcessingException.class, () -> courseService.deleteCourse(NOT_EXIST_ID));
    }

    @Test
    void testDeleteCourse_whenThrowsCourseNotFoundException_shouldShowExceptionMessage() {
        String messageNotToGet = "aaaaa";
        doThrow(CourseProcessingException.class)
                .when(courseRepositoryMock)
                .deleteById(NOT_EXIST_ID);

        CourseProcessingException exception = assertThrows(CourseProcessingException.class,
                () -> courseService.deleteCourse(NOT_EXIST_ID));

        assertEquals(CAN_NOT_DELETE_COURSE + NOT_EXIST_ID, exception.getMessage());
        assertNotEquals(messageNotToGet, exception.getMessage());
    }

}
