package com.everycodeacademy.backend.review.dto;

public record LikeResponse(Long submissionId, long likeCount, boolean liked) {
}
