package com.epam.rd.payload.request;

import com.epam.rd.annotations.PasswordMatches;
import com.epam.rd.annotations.ValidEmail;
import com.epam.rd.model.enumerations.Gender;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@PasswordMatches
@ValidEmail
public class UpdateUserProfileRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Size(min = 8)
    private String newPassword;
    private Gender gender;
    private int age;
}
