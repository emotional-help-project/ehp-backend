package com.epam.rd.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "advice_course")
@Data
public class AdviceCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Advice advice;
    @ManyToOne
    private Course course;
}
