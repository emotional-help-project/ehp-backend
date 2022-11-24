package com.epam.rd.controller;


import com.epam.rd.model.entity.Test;
import com.epam.rd.service.CommonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tests")
public class TestController extends BaseController<Test> {

    public TestController(CommonService<Test, Long> commonService) {
        super(commonService);
    }
}
