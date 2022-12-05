package com.epam.rd.model.dto;

import com.epam.rd.model.entity.Psychologist;
import com.epam.rd.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class ClaimForAppointmentDto {


    private Long id;
    private User user;
    private Psychologist psychologist;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate desiredDate;

    private String userPhone;
}
