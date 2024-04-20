package ru.practicum.ewm.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.server.models.comment.CommentDto;
import ru.practicum.ewm.server.models.comment.NewCommentDto;
import ru.practicum.ewm.server.models.comment.UpdateCommentRequest;
import ru.practicum.ewm.server.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users/{userId}/events/{eventId}/comments")
public class PrivateCommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> postComment(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @RequestBody @Valid NewCommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.postComment(userId, eventId, commentDto));
    }

    @GetMapping("/{commId}")
    public ResponseEntity<CommentDto> getComment(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @PathVariable Long commId) {
        return ResponseEntity.ok().body(commentService.getComment(userId, eventId, commId));
    }

    @PatchMapping("/{commId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @PathVariable Long commId,
            @RequestBody @Valid UpdateCommentRequest request) {
        return ResponseEntity.ok().body(commentService.updateComment(userId, eventId, commId, request));
    }

    @DeleteMapping("/{commId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @PathVariable Long commId) {
        commentService.deleteComment(userId, eventId, commId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComment(
            @PathVariable Long eventId) {
        return ResponseEntity.ok().body(commentService.getAllComments(eventId));
    }
}
