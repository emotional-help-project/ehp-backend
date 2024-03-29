package com.epam.rd.model.enumerations;

public enum Gender {

    MALE, FEMALE, OTHER;

    public static Gender fromString(String string) {
        return Enum.valueOf(Gender.class, string.trim().toUpperCase());
    }
}
