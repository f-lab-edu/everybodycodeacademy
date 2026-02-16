package com.everycodeacademy.backend.review.dto;

public record AiFeedbackResponse(Long submissionId, String model, String feedback, boolean fallback) {
}
