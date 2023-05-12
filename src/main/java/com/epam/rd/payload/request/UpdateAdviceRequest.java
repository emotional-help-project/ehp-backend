package com.epam.rd.payload.request;

import lombok.Data;

@Data
public class UpdateAdviceRequest {

    private Long id;
    private String title;
    private Long scoreFrom;
    private Long scoreTo;
    private Long testId;
}
