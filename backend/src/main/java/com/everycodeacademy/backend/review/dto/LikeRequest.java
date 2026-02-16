package com.everycodeacademy.backend.review.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LikeRequest(@NotBlank @Email String userEmail) {
}
