package com.example.mumul.domain.user.dto;

import com.example.mumul.domain.user.domain.User;

import java.time.LocalDateTime;

public record UserDto (
    String userId,
    String password,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){
    public static UserDto of(String userid, String password, String nickname) {
        return new UserDto(userid, password, null, null);
    }

    public static UserDto of(String userid, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new UserDto(userid, password, createdAt, updatedAt);
    }

    public static UserDto from(User entity) {
        return new UserDto(
                entity.getUserId(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public User toEntity() {
        return User.of(
                this.userId,
                this.password
        );
    }
}
