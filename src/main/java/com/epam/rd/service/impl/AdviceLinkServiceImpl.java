package com.epam.rd.service.impl;

import com.epam.rd.model.entity.AdviceLink;
import com.epam.rd.repository.AdviceLinkRepository;
import com.epam.rd.service.AdviceLinkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//TODO class and its functionality should be removed later and replaced by Cascade deleting of the mapped entities
@Service
@AllArgsConstructor
public class AdviceLinkServiceImpl implements AdviceLinkService {

    private AdviceLinkRepository adviceLinkRepository;

    @Transactional
    @Override
    public void deleteAdviceLink(Long id) {
        Optional<AdviceLink> adviceLink = adviceLinkRepository.findById(id);
        if (adviceLink.isPresent()) {
            adviceLinkRepository.deleteById(id);
        }
    }

}
