package com.epam.rd.controller;

import com.epam.rd.model.entity.TestType;
import com.epam.rd.service.CommonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/testTypes")
public class TestTypeController extends BaseController<TestType> {

    public TestTypeController(CommonService<TestType, Long> commonService) {
        super(commonService);
    }
}