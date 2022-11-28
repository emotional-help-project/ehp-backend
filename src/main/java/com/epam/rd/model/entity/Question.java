package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "questions")
@Getter
@Setter
@ToString
public class Question extends BaseEntity {
    @Column
    private Integer number;
    @ManyToOne
    private Test test;
    @Column
    private Boolean multiple_answers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(number, question.number) && Objects.equals(test, question.test) && Objects.equals(multiple_answers, question.multiple_answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, test, multiple_answers);
    }
}
