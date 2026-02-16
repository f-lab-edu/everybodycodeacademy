package com.everycodeacademy.backend.user.dto;

import jakarta.validation.constraints.*;

public record UserSignupRequest(
        @NotBlank @Size(min = 2, max = 20) String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password,
        @Pattern(regexp = "^\\d{10,11}$") String phoneNumber,
        @Size(max = 100) String address,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$") String birthDate,
        @Pattern(regexp = "^(MALE|FEMALE)$") String gender,
        @Size(max = 20) String nickname,
        @Pattern(regexp = "^(LOCAL|KAKAO|GOOGLE|NAVER)$") String loginProvider,
        String providerId
) {
}
