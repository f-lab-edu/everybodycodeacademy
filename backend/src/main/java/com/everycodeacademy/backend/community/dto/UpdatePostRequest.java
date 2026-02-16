package com.everycodeacademy.backend.community.dto;

import jakarta.validation.constraints.Size;

public record UpdatePostRequest(@Size(max = 120) String title, String body) {
}
