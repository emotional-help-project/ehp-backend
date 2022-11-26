package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "tests")
public class Test extends BaseEntity {

    @ManyToOne
    private TestType testType;
}
