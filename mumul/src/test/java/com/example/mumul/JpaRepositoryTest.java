package com.example.mumul;

import com.example.mumul.domain.comment.dao.CommentRepository;
import com.example.mumul.domain.post.dao.PostRepository;
import com.example.mumul.domain.post.domain.Post;
import com.example.mumul.global.config.JpaConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public JpaRepositoryTest(
            @Autowired PostRepository postRepository,
            @Autowired CommentRepository commentRepository
    ) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @DisplayName("select")
    @Test
    void selectTest() {
        //Given

        //When
        List<Post> post = postRepository.findAll();

        //Then
        assertThat(post)
                .isNotNull()
                .hasSize(10);
    }

    @DisplayName("insert")
    @Test
    void insertTest() {

        //Given
        long before = postRepository.count();

        //When
        Post savedPost = postRepository.save(Post.of("title", "content"));

        //Then
        assertThat(postRepository.count()).isEqualTo(before + 1);
    }

    @DisplayName("update")
    @Test
    void updateTest() {
        //Given
        Post savedPost = postRepository.save(Post.of("title", "content"));
        String expectedTitle = "title2";
        String expectedContent = "content2";

        //When
        savedPost.setTitle(expectedTitle);
        savedPost.setContent(expectedContent);

        //Then
        assertThat(savedPost.getTitle()).isEqualTo(expectedTitle);
        assertThat(savedPost.getContent()).isEqualTo(expectedContent);
    }

    @DisplayName("delete")
    @Test
    void deleteTest() {
        //Given
        Post savedPost = postRepository.save(Post.of("title", "content"));
        long before = postRepository.count();

        //When
        postRepository.delete(savedPost);

        //Then
        assertThat(postRepository.count()).isEqualTo(before - 1);
    }
}