package flab.userservice.dto;


import jakarta.validation.constraints.NotBlank;

public record UserLoginResquest(
       @NotBlank(message = "로그인 제공자는 필수입니다.")
       LoginProvider loginProvider,

       // LOCAL 로그인 전용
       String email,
       String password,

       // 소셜 로그인 전용
       String providerId) {
}
