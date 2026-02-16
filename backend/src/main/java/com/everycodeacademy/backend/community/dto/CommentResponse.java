package com.everycodeacademy.backend.community.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        Long postId,
        Long parentCommentId,
        String body,
        String author,
        LocalDateTime createdAt
) {
}
