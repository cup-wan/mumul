package com.example.mumul.domain.post.dto;

import com.example.mumul.domain.comment.dto.CommentDto;
import com.example.mumul.domain.post.domain.Post;
import com.example.mumul.domain.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;

public record PostWithCommentsDto(
        Long id,
        UserDto userDto,
        Set<CommentDto> commentDto,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static PostWithCommentsDto of(Long id, UserDto userDto, Set<CommentDto> postCommentDto, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new PostWithCommentsDto(id, userDto, postCommentDto, title, content, createdAt, modifiedAt);
    }

    public static PostWithCommentsDto from(Post entity) {
        return new PostWithCommentsDto(
                entity.getId(),
                UserDto.from(entity.getUser()),
                entity.getComment().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
                ,
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

}
