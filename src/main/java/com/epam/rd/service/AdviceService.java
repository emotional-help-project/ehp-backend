package com.epam.rd.service;

import com.epam.rd.model.dto.AdviceDto;
import com.epam.rd.payload.request.UpdateAdviceRequest;

import java.util.List;

public interface AdviceService {
    void deleteAdvice(Long id);

    AdviceDto updateAdvice(UpdateAdviceRequest updateAdviceRequest);

    List<AdviceDto> getAllAdvice();

    List<AdviceDto> getAllAdviceByTestId(Long testId);
}
