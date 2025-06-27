package flab.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserSignupRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank String password
) {
}

public record UserLoginRequest(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}

public record UserLoginResponse(
        String accessToken,
        String refreshToken
) {
}
