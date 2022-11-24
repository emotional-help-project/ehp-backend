package com.epam.rd.controller;

import com.epam.rd.model.entity.Type;
import com.epam.rd.service.CommonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/types")
public class TypeController extends BaseController<Type> {


    public TypeController(CommonService<Type, Long> commonService) {
        super(commonService);
    }
}