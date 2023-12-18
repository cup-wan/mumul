package com.example.mumul.domain.post.controller;

import com.example.mumul.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/{userId}")
    public String getPosts(@PathVariable String userId, ModelMap modelMap) {
        modelMap.addAttribute("posts", postService.getPost(userId));
        return "post/receive";
    }

    @GetMapping("/{userId}/posts/{postId}")
    public String getPost(@PathVariable String userId, @PathVariable Long postId, ModelMap modelMap) {
        modelMap.addAttribute("post", postService.getPostWithComments(postId));
        return "post/detail";
    }

}
