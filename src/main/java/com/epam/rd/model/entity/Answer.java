package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;


@Entity(name = "answers")
@Getter
@Setter
@ToString
public class Answer extends BaseEntity {
    @ManyToOne
    private Question question;

    @Column(nullable = false)
    private Long score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer answer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(question, answer.question) && Objects.equals(score, answer.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question, score);
    }
}
