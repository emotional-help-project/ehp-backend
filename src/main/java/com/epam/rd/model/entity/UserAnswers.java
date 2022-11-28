package com.epam.rd.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user_answers")
@Data
public class UserAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Question question;

    @OneToOne
    private Answer answer;

    @OneToOne
    private Session session;
}
