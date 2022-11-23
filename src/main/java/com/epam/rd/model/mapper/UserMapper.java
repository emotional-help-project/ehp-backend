package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper implements EntityMapper<UserDto, User> {

}

