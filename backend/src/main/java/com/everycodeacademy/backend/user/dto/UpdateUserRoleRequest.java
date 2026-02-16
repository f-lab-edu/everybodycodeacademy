package com.everycodeacademy.backend.user.dto;

import com.everycodeacademy.backend.user.entity.UserRole;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRoleRequest(@NotNull UserRole role) {
}
