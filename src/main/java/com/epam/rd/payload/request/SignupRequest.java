package com.epam.rd.payload.request;

import com.epam.rd.annotations.PasswordMatches;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

    @NotEmpty(message = "Name should not be empty!")
    private String firstname;

    @NotEmpty(message = "Username need!")
    private String username;
    @NotEmpty(message = "Password should not be empty ")
    @Size(min = 8)
    private String password;


}
