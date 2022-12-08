package com.epam.rd.model.dto;

import com.epam.rd.annotations.ValidEmail;
import com.epam.rd.model.enumerations.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class PsychologistDto {

    private Long id;

    @Size(min = 2, max = 20, message = "First Name should be between 2 and 20 characters")
    @NotBlank(message = "First Name should not be empty.")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last Name should be between 2 and 20 characters")
    @NotBlank(message = "Last Name should not be empty.")
    private String lastName;

    @ValidEmail(message = "Email must be in the correct format")
    @NotBlank(message = "Email should not be empty.")
    private String email;

    private String avatar;
    private Gender gender;

    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private int age;
    private String education;
    private String license;
    private String qualification;
}
