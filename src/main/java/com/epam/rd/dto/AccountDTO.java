package com.epam.rd.dto;

import com.epam.rd.enumirations.URoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private String  name;
    private String  username;
    private String  password;
    private URoles role;

}
