package com.epam.rd.model.mapper;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "role", target = "role")
    })
    UserDto userToDto(User user);

    User dtoToUser(UserDto userDto);

    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    UserDto userToDtoWithoutPassword(User user);
}

