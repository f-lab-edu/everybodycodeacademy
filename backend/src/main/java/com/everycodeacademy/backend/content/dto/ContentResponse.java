package com.everycodeacademy.backend.content.dto;

import com.everycodeacademy.backend.content.entity.ContentApprovalStatus;
import com.everycodeacademy.backend.content.entity.ContentCategory;

import java.time.LocalDateTime;

public record ContentResponse(
        Long id,
        String title,
        String summary,
        String body,
        String sourceUrl,
        String author,
        ContentCategory category,
        ContentApprovalStatus approvalStatus,
        String approvalComment,
        String approvedBy,
        LocalDateTime approvedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
