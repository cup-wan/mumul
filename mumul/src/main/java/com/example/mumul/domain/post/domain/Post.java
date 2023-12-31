package com.example.mumul.domain.post.domain;

import com.example.mumul.domain.comment.domain.Comment;
import com.example.mumul.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @Setter
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    @Setter
    private String title;  //제목

    @Column(nullable = false, length = 10000)
    @Setter
    private String content;  //내용

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<Comment> comment = new LinkedHashSet<>();

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  //ISO 8601
    private LocalDateTime createdAt;  //생성일시

    @Column(nullable = false)
    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;  //수정일시



    protected Post() {}

    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public static Post of(User user,String title, String content) {
        return new Post(user, title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return id != null && id.equals(post.id);  //id가 아직 영속화 안됐을 때 다 다른 것으로 간주
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
