package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "tests")
@Getter
@Setter
@ToString
public class Test extends BaseEntity {

    @ManyToOne
    private TestType testType;

    private String description;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test test)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(testType, test.testType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), testType);
    }
}
