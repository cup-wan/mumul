package com.example.mumul.domain.comment.domain;

import com.example.mumul.domain.post.domain.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String title;  //제목

    @Column(nullable = false, length = 10000)
    @Setter
    private String content;  //내용

    @Column(nullable = false, updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    private LocalDateTime createdAt;  //생성일시

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime updatedAt;  //수정일시

    @Setter
    @ManyToOne(optional = false)  //반드시 상위 post 있어야 함
    private Post post;  //상위 게시글

    protected Comment() {}

    private Comment(String title, String content, Post post) {
        this.title = title;
        this.content = content;
        this.post = post;
    }

    public static Comment of(String title, String content, Post post) {
        return new Comment(title, content, post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
