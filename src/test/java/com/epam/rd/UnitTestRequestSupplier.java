package com.epam.rd;

import com.epam.rd.payload.request.*;

import java.util.List;

import static com.epam.rd.model.enumerations.FieldType.STRING;
import static com.epam.rd.model.enumerations.Gender.MALE;
import static com.epam.rd.model.enumerations.Operator.EQUAL;
import static com.epam.rd.model.enumerations.SortDirection.ASC;

public class UnitTestRequestSupplier {

    public static SignupRequest signupRequest() {
        return new SignupRequest()
                .setFirstName("Ron")
                .setLastName("Smith")
                .setEmail("test3@email")
                .setPassword("Pass123!")
                .setConfirmPassword("Pass123!")
                .setGender(MALE)
                .setAge(25);
    }

    public static UpdateUserProfileRequest updateUserProfileRequest() {
        return new UpdateUserProfileRequest()
                .setId(123L)
                .setEmail("smith.r@gmail.com")
                .setAge(27);
    }

    public static SearchRequest searchUsersByFirstName(String firstName) {
        return new SearchRequest()
                .setFilters(List.of(new FilterRequest()
                        .setKey("firstName")
                        .setOperator(EQUAL)
                        .setFieldType(STRING)
                        .setValue(firstName)))
                .setSorts(List.of(new SortRequest()
                        .setKey("age")
                        .setDirection(ASC)))
                .setPage(null)
                .setSize(null);
    }
}
