package com.epam.rd.controller;

import com.epam.rd.model.entity.Question;
import com.epam.rd.service.CommonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions")
public class QuestionController extends BaseController<Question> {

    public QuestionController(CommonService<Question, Long> commonService) {
        super(commonService);
    }
}

