package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Degree {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private int score;

}
