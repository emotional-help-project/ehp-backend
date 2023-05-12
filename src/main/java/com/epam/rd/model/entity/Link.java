package com.epam.rd.model.entity;

import com.epam.rd.model.enumerations.LinkCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "links")
@ToString
public class Link extends BaseEntity{
    @Column(nullable = false)
    private String link;

    @Enumerated(EnumType.STRING)
    private LinkCategory linkCategory;

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
