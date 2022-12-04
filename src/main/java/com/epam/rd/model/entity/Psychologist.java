package com.epam.rd.model.entity;

import com.epam.rd.model.enumerations.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity(name = "psychologists")
@NoArgsConstructor
@Data
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Min(value = 5, message = "User age should not be less than 5 years old.")
    @Max(value = 150, message = "User age should be less than 150 years old.")
    private int age;

    private String education;

    @Column(nullable = false)
    private String license;

    private String qualification;

}
