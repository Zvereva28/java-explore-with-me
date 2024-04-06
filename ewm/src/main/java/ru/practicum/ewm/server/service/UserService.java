package ru.practicum.ewm.server.service;

import ru.practicum.ewm.server.models.user.User;
import ru.practicum.ewm.server.models.user.UserDto;

import java.util.List;

public interface UserService {
    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    User createUser(UserDto userDto);

    void deleteUser(Long userId);
}
