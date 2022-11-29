package com.epam.rd.payload.request;

import com.epam.rd.annotations.PasswordMatches;
import com.epam.rd.annotations.ValidEmail;
import com.epam.rd.annotations.ValidPassword;
import com.epam.rd.model.enumerations.Gender;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
@ValidEmail
@ValidPassword
public class SignupRequest {

    @Size(min = 2, max = 20, message = "First Name should be between 2 and 20 characters")
    @NotEmpty(message = "First Name should not be empty.")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last Name should be between 2 and 20 characters")
    @NotEmpty(message = "First Name should not be empty.")
    private String lastName;

    @NotEmpty(message = "Email should not be empty.")
    private String email;

    @ValidPassword(message = "Password must contain at least one digit, at least one lowercase and one uppercase Latin characters as well as at least one special character like ! @ # & ( ) etc.")
    @NotEmpty(message = "Password should not be empty.")
    @Size(min = 8, message = "Password size must be not less than 8 characters.")
    private String password;

    @NotEmpty(message = "Password does not match")
    private String confirmPassword;

    private Gender gender;

    @Min(value = 5)
    private int age;


}
