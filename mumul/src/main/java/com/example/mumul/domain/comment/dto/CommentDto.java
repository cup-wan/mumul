package com.example.mumul.domain.comment.dto;

import com.example.mumul.domain.comment.domain.Comment;
import com.example.mumul.domain.post.domain.Post;
import com.example.mumul.domain.user.domain.User;
import com.example.mumul.domain.user.dto.UserDto;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        Long postId,
        UserDto userDto,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static CommentDto of(Long PostId, UserDto userDto, String content) {
        return CommentDto.of(PostId, userDto, content);
    }

    public static CommentDto of(Long id, Long PostId, UserDto userDto, String content) {
        return CommentDto.of(null, PostId, userDto,  content, null, null);
    }

    public static CommentDto of(Long id, Long PostId, UserDto userDto, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new CommentDto(id, PostId, userDto, content, createdAt, updatedAt);
    }

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getPost().getId(),
                UserDto.from(entity.getUser()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public Comment toEntity(Post post, User user) {
        return Comment.of(
                post,
                user,
                content
        );
    }

}