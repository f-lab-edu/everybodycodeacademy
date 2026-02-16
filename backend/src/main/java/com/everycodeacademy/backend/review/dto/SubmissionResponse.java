package com.everycodeacademy.backend.review.dto;

import java.time.LocalDateTime;

public record SubmissionResponse(
        Long id,
        String title,
        String description,
        String language,
        String sourceCode,
        String authorEmail,
        String purpose,
        long likeCount,
        LocalDateTime createdAt
) {
}
