package com.epam.rd.payload.request;

import com.epam.rd.annotations.PasswordMatches;
import com.epam.rd.annotations.ValidEmail;
import com.epam.rd.annotations.ValidPassword;
import com.epam.rd.model.enumerations.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class UpdateUserProfileRequest {

    private Long id;

    @Nullable
    @Size(min = 2, max = 20, message = "First Name should be between 2 and 20 characters")
    private String firstName;

    @Nullable
    @Size(min = 2, max = 20, message = "Last Name should be between 2 and 20 characters")
    private String lastName;

    @ValidEmail(message = "Email must be in the correct format")
    private String email;

    @ValidPassword(message = "Password must contain at least one digit, at least one lowercase and one uppercase Latin characters as well as at least one special character like ! @ # & ( ) etc.")
    @Size(min = 8, message = "Password size must be not less than 8 characters.")
    private String newPassword;

    @Nullable
    private Gender gender;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Min(value = 5, message = "Age must be greater than or equal to 5")
    private Integer age;
}
