package ru.practicum.ewm.server.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.server.exceptions.ForbiddenException;
import ru.practicum.ewm.server.exceptions.NotFoundException;
import ru.practicum.ewm.server.mapper.CommentMapper;
import ru.practicum.ewm.server.models.comment.Comment;
import ru.practicum.ewm.server.models.comment.CommentDto;
import ru.practicum.ewm.server.models.comment.NewCommentDto;
import ru.practicum.ewm.server.models.comment.UpdateCommentRequest;
import ru.practicum.ewm.server.models.event.Event;
import ru.practicum.ewm.server.models.user.User;
import ru.practicum.ewm.server.service.CommentService;
import ru.practicum.ewm.server.storage.CommentRepository;
import ru.practicum.ewm.server.storage.EventRepository;
import ru.practicum.ewm.server.storage.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CommentMapper commentMapper;


    @Override
    @Transactional
    public CommentDto postComment(Long userId, Long eventId, NewCommentDto newDto) {
        User user = ifUserExistReturn(userId);
        Event event = ifEventExistReturn(eventId);
        Comment comment = commentRepository.save(commentMapper.toComment(newDto));
        comment.setAuthor(user);
        comment.setEvent(event);
        CommentDto commentDto = commentMapper.toCommentDto(comment);
        log.info("Пользователь с id={} оставил комментарий для события с id={}", userId, eventId);
        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto updateComment(Long userId, Long eventId, Long commId, UpdateCommentRequest request) {
        ifUserExistReturn(userId);
        ifEventExistReturn(eventId);
        Comment comment = ifCommentExistReturn(commId);

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new ForbiddenException("Вы не имеете права обновлять комментарий");
        }

        comment.setText(request.getText());
        CommentDto commentDto = commentMapper.toCommentDto(comment);

        log.info("Комментарий id={} изменен пользователем", commId);
        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto updateCommentByAdmin(Long eventId, Long commId, UpdateCommentRequest request) {
        ifEventExistReturn(eventId);
        Comment comment = ifCommentExistReturn(commId);
        comment.setText(request.getText());
        CommentDto commentDto = commentMapper.toCommentDto(comment);

        log.info("Комментарий id={} изменен админом", commId);
        return commentDto;
    }

    @Override
    @Transactional
    public void deleteComment(Long userId, Long eventId, Long commId) {
        Comment comment = ifCommentExistReturn(commId);
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new ForbiddenException("Вы не имеете право писать комментарий");
        }
        commentRepository.deleteById(commId);
        log.info("Удален комментарий id={}", commId);
    }

    @Override
    public CommentDto getComment(Long userId, Long eventId, Long commId) {
        ifUserExistReturn(userId);
        ifEventExistReturn(eventId);
        CommentDto comment = commentMapper.toCommentDto(ifCommentExistReturn(commId));
        log.info("Пользователь id={} комментарий id={}", userId, commId);

        return comment;
    }

    @Override
    public List<CommentDto> getAllComments(Long eventId) {
        ifEventExistReturn(eventId);
        List<CommentDto> commentDtos = commentRepository.findAllByEventId(eventId).stream()
                .map(commentMapper::toCommentDto)
                .collect(Collectors.toList());
        log.info("К событию id={} получены комментарии", eventId);

        return commentDtos;
    }

    @Override
    @Transactional
    public void deleteCommentByAdmin(Long eventId, Long commId) {
        ifCommentExistReturn(commId);
        commentRepository.deleteById(commId);
        log.info("Админ удалил комментарий id={}", commId);
    }

    @Override
    public CommentDto getPublicComment(Long eventId, Long commId) {
        ifEventExistReturn(eventId);
        CommentDto commentDto = commentMapper.toCommentDto(ifCommentExistReturn(commId));
        log.info("К событию id={} получен комментарий  id={}", eventId, commId);

        return commentDto;
    }

    private Comment ifCommentExistReturn(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Комментария с id=%d нет в базе", id)));
    }

    private User ifUserExistReturn(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Пользователя с id=%d нет в базе", id)));
    }

    private Event ifEventExistReturn(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("События с id=%d нет в базе", id)));
    }
}
