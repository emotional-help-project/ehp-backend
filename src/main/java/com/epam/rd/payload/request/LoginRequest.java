package com.epam.rd.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class LoginRequest {

    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

}
