package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class EmotionMapResponse {

    private String testTitle;
    private List<TestResultStatisticsResponse> testResultStatistics;
}
