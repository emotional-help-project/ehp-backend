package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Getter
@Setter
@Entity(name = "links")
public class Link extends BaseEntity{
    @Column
    private String link;


}
