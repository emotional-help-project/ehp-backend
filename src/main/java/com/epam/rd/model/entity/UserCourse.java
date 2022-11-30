package com.epam.rd.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user_course")
@Data
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

}
