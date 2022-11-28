package com.epam.rd.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "links")
@ToString
public class Link extends BaseEntity{
    @Column
    private String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link link1)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(link, link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), link);
    }
}
