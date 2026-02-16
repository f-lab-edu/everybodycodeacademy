package com.everycodeacademy.backend.content.dto;

import com.everycodeacademy.backend.content.entity.ContentCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateContentRequest(
        @NotBlank @Size(max = 120) String title,
        @Size(max = 300) String summary,
        @NotBlank String body,
        @Size(max = 300) String sourceUrl,
        @NotBlank @Size(max = 60) String author,
        @NotNull ContentCategory category
) {
}
