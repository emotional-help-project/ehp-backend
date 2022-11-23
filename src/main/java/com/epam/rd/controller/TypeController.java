package com.epam.rd.controller;

import com.epam.rd.model.entity.Type;
import com.epam.rd.service.TypeService;

import com.epam.rd.service.impl.TypeServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @GetMapping()
    public List<Type> getAll() {
            return typeService.getAll();

    }

}