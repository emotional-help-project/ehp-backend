package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 */
@Getter
@Setter
@Entity
public class Advice extends BaseEntity{
    @Column
    private int scoreFrom;
    @Column
    private int scoreTo;
@ManyToOne
    private Test test;
@ManyToOne
    private Link link;

}
