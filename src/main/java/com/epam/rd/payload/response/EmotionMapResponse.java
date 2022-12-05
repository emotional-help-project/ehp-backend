package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class EmotionMapResponse {

    private List<TestResultStatisticsResponse> testResultStatistics;
}
