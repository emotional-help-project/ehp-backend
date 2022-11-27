package com.epam.rd.payload.response;

import com.epam.rd.model.entity.Test;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Accessors(chain = true)
public class TestPageForUserResponse {

    private List<IncompleteTestResponse> incompleteTests;
    private Page<Test> tests;
}
