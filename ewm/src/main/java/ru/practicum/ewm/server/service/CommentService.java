package ru.practicum.ewm.server.service;

import ru.practicum.ewm.server.models.comment.CommentDto;
import ru.practicum.ewm.server.models.comment.NewCommentDto;
import ru.practicum.ewm.server.models.comment.UpdateCommentRequest;

import java.util.List;

public interface CommentService {
    CommentDto postComment(Long userId, Long eventId, NewCommentDto commentDto);

    CommentDto updateComment(Long userId, Long eventId, Long commId, UpdateCommentRequest request);

    void deleteComment(Long userId, Long eventId, Long commId);

    void deleteCommentByAdmin(Long eventId, Long commId);

    CommentDto getComment(Long userId, Long eventId, Long commId);

    CommentDto getPublicComment(Long eventId, Long commId);

    List<CommentDto> getAllComments(Long eventId);

    CommentDto updateCommentByAdmin(Long eventId, Long commId, UpdateCommentRequest request);


}
