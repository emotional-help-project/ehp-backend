package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Test;
import com.epam.rd.model.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class SessionDto {

    private Long id;
    private User user;
    private Test test;
    private Boolean isFinished;
}
