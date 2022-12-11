package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestShortDetailsResponse {

    private Long testId;
    private String testTitle;
    private String imageUrl;

}
