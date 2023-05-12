package com.epam.rd.payload.response;

import com.epam.rd.model.dto.LinkDto;
import com.epam.rd.model.entity.TestType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserTestEmotionStatistics {

    String testTitle;
    String testTypeTitle;
    LocalDateTime dateTime;
    Long result;
    String adviceDescription;
    private List<LinkDto> links;
}
