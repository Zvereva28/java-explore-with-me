package ru.practicum.ewm.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.server.exceptions.NotFoundException;
import ru.practicum.ewm.server.mapper.UserMapper;
import ru.practicum.ewm.server.models.user.User;
import ru.practicum.ewm.server.models.user.UserDto;
import ru.practicum.ewm.server.service.UserService;
import ru.practicum.ewm.server.storage.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        if (ids == null) {
            log.info("Получен список пользователей");
            List<User> users = userRepository.findAll(PageRequest.of(from, size)).toList();
            return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
        } else {
            Pageable pageable = PageRequest.of(from, size);

            log.info("Получен список пользователей с id={}", ids);
            List<User> users = userRepository.findAllByIdAndPage(ids, pageable).toList();
            return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
        }
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(userMapper.toUser(userDto));

        log.info("Создан пользователь - {}", user);
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        findUserByIdOrElseThrow(userId);
        userRepository.deleteById(userId);
        log.info("Пользователь с id='{}' - удален", userId);
    }

    private void findUserByIdOrElseThrow(Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Пользователя с id=%d нет в базе", id)
        ));
    }
}
