package com.epam.rd.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "advice_links")
@Data
@Accessors(chain = true)
public class AdviceLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Advice advice;
    @ManyToOne
    private Link link;
}
