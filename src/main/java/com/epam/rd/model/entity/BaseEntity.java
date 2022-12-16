package com.epam.rd.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base entity created for avoid DRY.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 2500)
    @Column(nullable = false, length = 2500)
    private String title;

}
