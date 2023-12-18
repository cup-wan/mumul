package com.example.mumul.domain.post.dto;

import com.example.mumul.domain.post.domain.Post;
import com.example.mumul.domain.user.domain.User;
import com.example.mumul.domain.user.dto.UserDto;

import java.time.LocalDateTime;

public record PostDto (
    Long id,
    UserDto userDto,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){
    public static PostDto of(UserDto userDto, String title, String content){
        return new PostDto(null, userDto, title, content, null, null);
    }
    public static PostDto of(Long id, UserDto userDto, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new PostDto(id, userDto, title, content, createdAt, updatedAt);
    }

    public static PostDto from(Post entity) {
        return new PostDto(
                entity.getId(),
                UserDto.from(entity.getUser()),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public Post toEntity(User user) {
        return Post.of(
                user,
                this.title,
                this.content
        );
    }
}
