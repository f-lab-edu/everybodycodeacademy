package com.everycodeacademy.backend.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(
        Long parentCommentId,
        @NotBlank String body,
        @NotBlank @Size(max = 60) String author
) {
}
