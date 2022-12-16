package com.epam.rd.payload.request;

import com.epam.rd.annotations.ValidEmail;
import com.epam.rd.model.enumerations.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
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

    @Nullable
    private Gender gender;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Min(value = 5, message = "Age must be greater than or equal to 5")
    private Integer age;
}
