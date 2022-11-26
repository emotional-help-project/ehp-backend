package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class Advice extends BaseEntity {

    @Column(name = "score_from")
    private Long scoreFrom;
    @Column(name = "score_to")
    private Long scoreTo;
    @ManyToOne
    private Test test;

}
