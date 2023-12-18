package com.example.mumul.domain.post.service;

import com.example.mumul.domain.post.dao.PostRepository;
import com.example.mumul.domain.post.domain.Post;
import com.example.mumul.domain.post.dto.PostDto;
import com.example.mumul.domain.post.dto.PostWithCommentsDto;
import com.example.mumul.domain.user.dao.UserRepository;
import com.example.mumul.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostWithCommentsDto getPostWithComments(Long PostId) {
        return postRepository.findById(PostId)
                .map(PostWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않음"));
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPost(String userId) {
        return postRepository.findById(userId)
                .stream()
                .map(PostDto::from)
                .toList();
    }

    public void savePost(PostDto dto) {
        User user = userRepository.getReferenceById(dto.userDto().userId());
        Post post = dto.toEntity(user);
        postRepository.save(post);
    }

    public void updatePost(Long PostId, PostDto dto) {
        try {
            Post Post = postRepository.getReferenceById(PostId);
            User User = userRepository.getReferenceById(dto.userDto().userId());
        } catch (EntityNotFoundException e) {
            log.warn("수정 실패");
        }
    }

    public void deletePost(long PostId, String userId) {
        Post Post = postRepository.getReferenceById(PostId);
        postRepository.deleteByIdAndUser_UserId(PostId, userId);
        postRepository.flush();
    }

    public long getPostCount() {
        return postRepository.count();
    }

}