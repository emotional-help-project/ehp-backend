package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Question extends BaseEntity {
    @Column
    private Integer number;
    @ManyToOne
    private Test test;
    @Column
    private Boolean multiple_answers;

}
