package com.example.mumul.domain.comment.service;

import com.example.mumul.domain.comment.dao.CommentRepository;
import com.example.mumul.domain.comment.domain.Comment;
import com.example.mumul.domain.comment.dto.CommentDto;
import com.example.mumul.domain.post.dao.PostRepository;
import com.example.mumul.domain.post.domain.Post;
import com.example.mumul.domain.user.dao.UserRepository;
import com.example.mumul.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> getComments(Long postId) {
        return commentRepository.findByPost_Id(postId)
                .stream()
                .map(CommentDto::from)
                .toList();
    }

    public void saveComment(CommentDto dto) {
        try {
            Post post = postRepository.getReferenceById(dto.postId());
            User user = userRepository.getReferenceById(dto.userDto().userId());
            commentRepository.save(dto.toEntity(post, user));
        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패");
        }
    }

    public void updateComment(Long commentId, CommentDto dto) {
        try {
            Comment comment = commentRepository.getReferenceById(commentId);
            User User = userRepository.getReferenceById(dto.userDto().userId());
        } catch (EntityNotFoundException e) {
            log.warn("수정 실패");
        }
    }

    public void deleteComment(Long commentId, String userId) {
        commentRepository.deleteByIdAndUser_UserId(commentId, userId);
    }


}