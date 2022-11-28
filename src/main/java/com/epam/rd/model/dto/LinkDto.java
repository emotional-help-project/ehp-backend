package com.epam.rd.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class LinkDto {

    private Long id;
    private String title;
    private String link;
}
