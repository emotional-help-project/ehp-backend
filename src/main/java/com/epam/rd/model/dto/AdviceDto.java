package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Test;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdviceDto {

    private Long id;
    private String title;
    private Long scoreFrom;
    private Long scoreTo;
    private Test test;
}
