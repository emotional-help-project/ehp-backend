package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Test extends BaseEntity {

@ManyToOne
    private Type type;
}
