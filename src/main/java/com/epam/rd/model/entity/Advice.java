package com.epam.rd.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
public class Advice extends BaseEntity {

    @Column(name = "score_from")
    private Long scoreFrom;
    @Column(name = "score_to")
    private Long scoreTo;
    @ManyToOne
    private Test test;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advice advice)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(scoreFrom, advice.scoreFrom) && Objects.equals(scoreTo, advice.scoreTo) && Objects.equals(test, advice.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scoreFrom, scoreTo, test);
    }
}
