package com.everycodeacademy.backend.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotBlank @Size(max = 120) String title,
        @NotBlank String body,
        @NotBlank @Size(max = 60) String author
) {
}
