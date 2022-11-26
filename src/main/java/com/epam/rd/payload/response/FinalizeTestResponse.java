package com.epam.rd.payload.response;

import com.epam.rd.model.entity.Link;
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
    private List<Link> links;
}
