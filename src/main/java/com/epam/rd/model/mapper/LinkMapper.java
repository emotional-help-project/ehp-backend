package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.LinkDto;
import com.epam.rd.model.entity.Link;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class LinkMapper implements EntityMapper<LinkDto, Link> {

}