package com.epam.rd.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "user_course")
@Data
@Accessors(chain = true)
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

}
