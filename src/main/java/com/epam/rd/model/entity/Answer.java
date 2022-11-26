package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "answers")
public class Answer extends BaseEntity {
    @ManyToOne
    private Question question;

    @Column(nullable = false)
    private Long score;
}
