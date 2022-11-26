package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Answer;
import com.epam.rd.model.entity.Question;
import com.epam.rd.model.entity.Session;
import com.epam.rd.model.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAnswersDto {

    private Long id;
    private User user;
    private Question question;
    private Answer answer;
    private Session session;
}
