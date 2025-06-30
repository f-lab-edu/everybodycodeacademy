package flab.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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

public record UserLoginRequest(
        @NotBlank(message = "로그인 제공자는 필수입니다.")
        LoginProvider loginProvider,

        // LOCAL 로그인 전용
        String email,
        String password,

        // 소셜 로그인 전용
        String providerId
) {}

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
        LocalDateTime createdAt,
        LocalDateTime lastLoginAt
) {}

