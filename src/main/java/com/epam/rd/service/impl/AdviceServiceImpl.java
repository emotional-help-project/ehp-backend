package com.epam.rd.service.impl;

import com.epam.rd.exceptions.TestProcessingException;
import com.epam.rd.exceptions.UserProcessingException;
import com.epam.rd.model.dto.AdviceDto;
import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.Test;
import com.epam.rd.model.entity.User;
import com.epam.rd.model.mapper.AdviceMapper;
import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.UpdateAdviceRequest;
import com.epam.rd.repository.AdviceRepository;
import com.epam.rd.repository.TestRepository;
import com.epam.rd.service.AdviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdviceServiceImpl implements AdviceService {

    private static final String CANNOT_FIND_ADVICE_BY_ID = "Cannot find advice with ID=";
    private static final String CANNOT_FIND_TEST = "Cannot find test with ID=";

    private final AdviceRepository adviceRepository;
    private final TestRepository testRepository;
    private final AdviceMapper adviceMapper;


    @Transactional
    @Override
    public void deleteAdvice(Long id) {
        Optional<Advice> advice = adviceRepository.findById(id);
        if (advice.isPresent()) {
            adviceRepository.deleteById(id);
        }
    }

    @Override
    public AdviceDto updateAdvice(UpdateAdviceRequest updateAdviceRequest) {

        Advice updatedAdvice = adviceRepository.findById(updateAdviceRequest.getId())
                .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_ADVICE_BY_ID + updateAdviceRequest.getId()));

        if (updateAdviceRequest.getTitle() != null) {
            updatedAdvice.setTitle(updateAdviceRequest.getTitle());
        }

        if (updateAdviceRequest.getScoreFrom() != null) {
            updatedAdvice.setScoreFrom(updateAdviceRequest.getScoreFrom());
        }

        if (updateAdviceRequest.getScoreTo() != null) {
            updatedAdvice.setScoreTo(updateAdviceRequest.getScoreTo());
        }

        if (updateAdviceRequest.getTestId() != null) {
            Test test = testRepository.findById(updateAdviceRequest.getTestId())
                    .orElseThrow(() -> new TestProcessingException(CANNOT_FIND_TEST + updateAdviceRequest.getTestId()));

            updatedAdvice.setTest(test);
        }

        adviceRepository.save(updatedAdvice);
        return adviceMapper.toDto(updatedAdvice);
    }

}
