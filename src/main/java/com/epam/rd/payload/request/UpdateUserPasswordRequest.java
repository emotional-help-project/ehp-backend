package com.epam.rd.payload.request;

import com.epam.rd.annotations.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateUserPasswordRequest {

    private Long userId;

    @ValidPassword(message = "Password must contain at least one digit, at least one lowercase and one uppercase Latin characters as well as at least one special character like ! @ # & ( ) etc.")
    @Size(min = 8, message = "Password size must be not less than 8 characters.")
    private String newPassword;
}
