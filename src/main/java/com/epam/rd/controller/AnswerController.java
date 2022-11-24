package com.epam.rd.controller;


import com.epam.rd.model.entity.Answer;
import com.epam.rd.service.CommonService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/answers")
public class AnswerController extends BaseController<Answer> {

    public AnswerController(CommonService<Answer, Long> commonService) {
        super(commonService);
    }
}
