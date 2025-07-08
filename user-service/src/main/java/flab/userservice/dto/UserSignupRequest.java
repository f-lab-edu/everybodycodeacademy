package flab.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserSignupRequest(
        @NotBlank @Size(min = 2, max = 20)
        String username,

        @NotBlank @Email
        String email,

        @NotBlank @Size(min = 8)
        String password,

        @Pattern(regexp = "^\\d{10,11}$")
        String phoneNumber,

        @Size(max = 100)
        String address,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식은 yyyy-MM-dd 여야 합니다.")
        String birthDate,

        @Pattern(regexp = "^(MALE|FEMALE)$")
        String gender,

        @Size(max = 20)
        String nickname,

        @Pattern(regexp = "^(LOCAL|KAKAO|GOOGLE|NAVER)$")
        String loginProvider,

        String providerId // 소셜 ID (예: 카카오 사용자 번호)
) {}
