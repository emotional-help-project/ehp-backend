package com.epam.rd.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class LoginRequest {

    @NotEmpty(message = "Telefon raqam bo'sh bo'lmasligi kerak")
    private String username;
    @NotEmpty(message = "Parol bo'sh bo'lmasligi kerak")
    private String password;

}
