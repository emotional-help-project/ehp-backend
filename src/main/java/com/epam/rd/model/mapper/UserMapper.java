package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper implements EntityMapper<UserDto, User> {

    abstract UserDto mapToDto(User user);

    abstract User mapToUser(UserDto user);

    public UserDto toDto(User user) {
        return mapToDto(user);
    }

    public User toEntity(UserDto userDto) {
        return mapToUser(userDto);
    }

}

