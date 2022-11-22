package com.epam.rd.payload.request;

import com.epam.rd.annotations.PasswordMatches;
import com.epam.rd.annotations.ValidEmail;
import com.epam.rd.model.enumerations.Gender;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
@ValidEmail
public class SignupRequest {

    @NotEmpty(message = "First Name should not be empty.")
    private String firstName;

    @NotEmpty(message = "First Name should not be empty.")
    private String lastName;

    @NotEmpty(message = "Email should not be empty.")
    private String email;

    @NotEmpty(message = "Password should not be empty.")
    @Size(min = 8)
    private String password;

    private Gender gender;

    private int age;


}
