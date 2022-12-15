package com.epam.rd.service.impl;

import com.epam.rd.model.entity.Advice;
import com.epam.rd.repository.AdviceRepository;
import com.epam.rd.service.AdviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;

    @Transactional
    @Override
    public void deleteAdvice(Long id) {
        Optional<Advice> advice = adviceRepository.findById(id);
        if (advice.isPresent()) {
            adviceRepository.deleteById(id);
        }
    }
    
}
