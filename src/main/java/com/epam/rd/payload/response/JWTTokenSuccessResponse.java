package com.epam.rd.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {

    private boolean success;
    private String token;
    private Long userId;
    private String firstName;
}
