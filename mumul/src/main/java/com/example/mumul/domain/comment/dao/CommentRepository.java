package com.example.mumul.domain.comment.dao;

import com.example.mumul.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(Long PostId);
    void deleteByIdAndUser_UserId(Long commentId, String userId);
}
