package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Test;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class QuestionDto {

    private Long id;
    private String title;
    private Integer number;
    private Test test;
    private Boolean multiple_answers;
}
