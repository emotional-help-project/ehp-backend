package com.epam.rd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "courses")
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Course extends BaseEntity {

    @Column(columnDefinition = "varchar(2500)")
    private String description;

    @Column(name = "date_created", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateCreated;

    @DecimalMin(value = "0.00")
    @Column
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(description, course.description) && Objects.equals(dateCreated, course.dateCreated) &&
                Objects.equals(price, course.price) && Objects.equals(imageUrl, course.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, dateCreated, price, imageUrl);
    }
}
