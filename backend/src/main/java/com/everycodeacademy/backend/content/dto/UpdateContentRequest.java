package com.everycodeacademy.backend.content.dto;

import com.everycodeacademy.backend.content.entity.ContentCategory;
import jakarta.validation.constraints.Size;

public record UpdateContentRequest(
        @Size(max = 120) String title,
        @Size(max = 300) String summary,
        String body,
        @Size(max = 300) String sourceUrl,
        @Size(max = 60) String author,
        ContentCategory category
) {
}
