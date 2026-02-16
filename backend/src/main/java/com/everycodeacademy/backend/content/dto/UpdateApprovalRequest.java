package com.everycodeacademy.backend.content.dto;

import com.everycodeacademy.backend.content.entity.ContentApprovalStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateApprovalRequest(
        @NotNull ContentApprovalStatus status,
        @Size(max = 500) String comment
) {
}
