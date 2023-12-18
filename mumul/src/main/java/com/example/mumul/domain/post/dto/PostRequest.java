//package com.example.mumul.domain.post.dto;
//
//public record PostRequest(
//        String title,
//        String content,
//        String hashtag
//) {
//
//    public static PostRequest of(String title, String content, String hashtag) {
//        return new PostRequest(title, content, hashtag);
//    }
//
//    public PostDto toDto() {
//        return PostDto.of(
//                UserDto,
//                title,
//                content
//        );
//    }
//
//}