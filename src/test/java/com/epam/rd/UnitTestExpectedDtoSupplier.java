package com.epam.rd;

import com.epam.rd.model.dto.CourseDto;
import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.enumerations.URole;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.epam.rd.model.enumerations.Gender.FEMALE;
import static com.epam.rd.model.enumerations.Gender.MALE;
import static com.epam.rd.model.enumerations.URole.USER;

public class UnitTestExpectedDtoSupplier {

    public static UserDto createUserDto() {
        return new UserDto()
                .setFirstName("Ron")
                .setLastName("Smith")
                .setEmail("test3@email")
                .setGender(MALE)
                .setAge(25);
    }

    public static List<UserDto> createUserDtoList() {

        return List.of(
                new UserDto(123L, "Ron", "Smith", "smith@email.com", MALE, 34, USER),

                new UserDto(124L, "Kate", "Brown", "brown@email.com",
                        FEMALE, 28, USER));
    }

    public static CourseDto createCourseDto() {
        return new CourseDto()
                .setId(123L)
                .setTitle("Managing Emotions in Times of Uncertainty & Stress")
                .setDescription("Developed by the Yale Center for Emotional Intelligence, " +
                        "Managing Emotions in Times of Uncertainty & Stress will provide participants with " +
                        "the knowledge, skills, and strategies to understand and manage their emotions " +
                        "and those of their students. The 10-hour online course is designed for school staff, " +
                        "including teachers, paraprofessionals, counselors, principals, and non-teaching " +
                        "staff in preK-12 schools.")
                .setPrice(new BigDecimal("300.00"))
                .setImageUrl("some/url/here")
                .setDateCreated(LocalDateTime.now());
    }

}
