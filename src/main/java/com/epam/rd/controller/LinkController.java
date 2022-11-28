package com.epam.rd.controller;

import com.epam.rd.model.entity.Link;

import com.epam.rd.service.impl.LinkServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/links")
public class LinkController extends BaseController<Link> {
    public LinkController(LinkServiceImpl service){
        super(service);
    }
}
