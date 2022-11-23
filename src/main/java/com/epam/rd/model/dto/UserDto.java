package com.epam.rd.model.dto;

import com.epam.rd.model.enumerations.Gender;
import com.epam.rd.model.enumerations.URole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Gender gender;
    private int age;
    private URole role;
}
