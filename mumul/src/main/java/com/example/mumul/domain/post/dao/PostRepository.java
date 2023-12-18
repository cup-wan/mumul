package com.example.mumul.domain.post.dao;

import com.example.mumul.domain.comment.domain.Comment;
import com.example.mumul.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
    void deleteByIdAndUser_UserId(Long commentId, String userId);
    List<Post> findByUserId(String userId);
}
