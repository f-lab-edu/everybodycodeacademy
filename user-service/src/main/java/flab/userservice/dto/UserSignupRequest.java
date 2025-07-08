package flab.userservice.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserSignupRequest(
        @NotBlank @Size(min = 2, max = 20)
        String username,

        @NotBlank @Email
        String email,

        @NotBlank @Size(min = 8)
        String password,

        @NotBlank @Pattern(regexp = "^\\d{10,11}$")
        String phoneNumber,

        @Size(max = 100)
        String address,

        @Past
        LocalDate birthDate,

        @Pattern(regexp = "^(MALE|FEMALE)$")
        String gender,

        @Size(max = 20)
        String nickname,

        @Pattern(regexp = "^(LOCAL|KAKAO|GOOGLE|NAVER)$")
        String loginProvider,

        String providerId // 소셜 ID (예: 카카오 사용자 번호)
) {}
