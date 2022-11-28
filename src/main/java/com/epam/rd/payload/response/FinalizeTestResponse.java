package com.epam.rd.payload.response;

import com.epam.rd.model.dto.LinkDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FinalizeTestResponse {

    private String adviceDescription;
    private long scoreFrom;
    private long scoreTo;
    private long userScore;
    private List<LinkDto> links;
}
