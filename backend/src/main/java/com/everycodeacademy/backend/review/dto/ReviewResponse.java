package com.everycodeacademy.backend.review.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        Long submissionId,
        String reviewerEmail,
        String comment,
        LocalDateTime createdAt
) {
}
