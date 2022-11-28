package com.epam.rd.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "type_links")
@Data
public class TypeLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TestType testType;
    @ManyToOne
    private Link link;
}
