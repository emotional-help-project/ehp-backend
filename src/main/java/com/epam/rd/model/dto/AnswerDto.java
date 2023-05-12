package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Question;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class AnswerDto {

    private Long id;
    private String title;
    private Question question;
    private Long score;
}
