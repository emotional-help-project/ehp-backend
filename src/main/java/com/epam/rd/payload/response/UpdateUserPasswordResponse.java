package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateUserPasswordResponse {

    private Long userId;
    private String newPassword;
}
