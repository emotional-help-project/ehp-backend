package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TestResultStatisticsResponse {

    private LocalDateTime testDateTime;
    private Long result;
}
