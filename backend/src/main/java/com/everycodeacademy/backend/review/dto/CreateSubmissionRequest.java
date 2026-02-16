package com.everycodeacademy.backend.review.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSubmissionRequest(
        @NotBlank @Size(max = 120) String title,
        String description,
        @NotBlank @Size(max = 40) String language,
        @NotBlank String sourceCode,
        @NotBlank @Email String authorEmail,
        @Size(max = 120) String purpose
) {
}
