package com.epam.rd.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "sessions")
@Data
@Accessors(chain = true)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Test test;

    @Column(name = "finished")
    private Boolean isFinished;

}
