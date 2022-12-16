package com.epam.rd;

import com.epam.rd.model.entity.User;

import static com.epam.rd.model.enumerations.Gender.FEMALE;
import static com.epam.rd.model.enumerations.URole.USER;

public class UnitTestExpectedEntitySupplier {

    public static User createUser() {
        return new User()
                .setId(123L)
                .setEmail("aaa@bbb.ccc")
                .setFirstName("UserFirstName")
                .setLastName("UserLastName")
                .setAge(26)
                .setGender(FEMALE)
                .setRole(USER);
    }
}
