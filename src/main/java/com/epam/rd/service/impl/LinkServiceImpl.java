package com.epam.rd.service.impl;

import com.epam.rd.model.entity.Link;
import com.epam.rd.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl extends BaseServiceImpl<Link,Long>{

    public LinkServiceImpl(BaseRepository<Link> baseRepository) {
        super(baseRepository);
    }
}
