package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewm.server.models.user.User;
import ru.practicum.ewm.server.models.user.UserDto;
import ru.practicum.ewm.server.models.user.UserShort;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    UserShort toUserShort(User user);
}
