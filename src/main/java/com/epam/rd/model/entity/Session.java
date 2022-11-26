package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "sessions")
@RequiredArgsConstructor
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
