package com.epam.rd.service;

import com.epam.rd.model.dto.AdviceDto;
import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.UpdateAdviceRequest;

public interface AdviceService {
    void deleteAdvice(Long id);

    AdviceDto updateAdvice(UpdateAdviceRequest updateAdviceRequest);
}
