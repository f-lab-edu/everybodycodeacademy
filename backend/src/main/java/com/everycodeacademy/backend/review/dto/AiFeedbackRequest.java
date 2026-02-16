package com.everycodeacademy.backend.review.dto;

import jakarta.validation.constraints.Size;

public record AiFeedbackRequest(@Size(max = 200) String focus) {
}
