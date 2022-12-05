package com.epam.rd.model.dto;

import com.epam.rd.model.enumerations.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PsychologistDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private int age;
    private String education;
    private String license;
    private String qualification;
}
