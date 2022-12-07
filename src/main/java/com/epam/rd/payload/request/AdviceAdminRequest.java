package com.epam.rd.payload.request;

import com.epam.rd.model.dto.LinkDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdviceAdminRequest {

    private String adviceTitle;
    private Long scoreFrom;
    private Long scoreTo;
    private Long testId;
    private List<Long> linkIds;
}
