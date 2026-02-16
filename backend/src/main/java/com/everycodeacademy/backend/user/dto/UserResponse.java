package com.everycodeacademy.backend.user.dto;

import com.everycodeacademy.backend.user.entity.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String email,
        String phoneNumber,
        String address,
        LocalDate birthDate,
        String gender,
        String nickname,
        String loginProvider,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime lastLoginAt
) {
}
