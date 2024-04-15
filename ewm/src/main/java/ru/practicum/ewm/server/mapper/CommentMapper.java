package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.server.models.comment.Comment;
import ru.practicum.ewm.server.models.comment.CommentDto;
import ru.practicum.ewm.server.models.comment.NewCommentDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CommentMapper {

    Comment toComment(NewCommentDto newCommentDto);

    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CommentDto toCommentDto(Comment comment);
}
