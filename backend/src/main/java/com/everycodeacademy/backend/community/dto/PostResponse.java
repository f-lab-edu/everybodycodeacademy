package com.everycodeacademy.backend.community.dto;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String body,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
