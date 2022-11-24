package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Answer extends BaseEntity{
    @ManyToOne
    private Question question;
    @OneToOne
    private Degree degree;
}
