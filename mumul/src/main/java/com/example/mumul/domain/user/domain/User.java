package com.example.mumul.domain.user.domain;

import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class User {

    @Id
    @Column(length = 50)
    private String userId;

    @Setter @Column(nullable = false)
    private String password;

    @Setter @Column(length = 100)
    private String email;

    @Setter @Column(length = 50)
    private String nickname;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Setter @Column
    private String status;

    protected User() {}

    private User(String userId, String password, String email, String nickname, String status) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
    }

    public static User of(String userId, String password, String email, String nickname, String status) {
        return new User(userId, password, email, nickname, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userId != null && userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
