package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Question;
import com.epam.rd.model.entity.TestType;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TestDto {

    private Long id;
    private String title;
    private TestType testType;

}
