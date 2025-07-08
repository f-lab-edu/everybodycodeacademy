package flab.userservice.dto;

import java.time.LocalDate;

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
        LocalDate createdAt,
        LocalDate lastLoginAt
) {}