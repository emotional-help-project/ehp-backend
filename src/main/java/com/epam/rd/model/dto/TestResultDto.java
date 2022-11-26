package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.Session;
import com.epam.rd.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TestResultDto {

    private Long id;
    private Long result;
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

    private User user;
    private Advice advice;
    private Session session;
}
