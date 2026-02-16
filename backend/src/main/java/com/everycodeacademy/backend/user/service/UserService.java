package com.everycodeacademy.backend.user.service;

import com.everycodeacademy.backend.security.JwtTokenProvider;
import com.everycodeacademy.backend.security.TokenStoreService;
import com.everycodeacademy.backend.user.dto.*;
import com.everycodeacademy.backend.user.entity.User;
import com.everycodeacademy.backend.user.entity.UserRole;
import com.everycodeacademy.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenStoreService tokenStoreService;
    private final String bootstrapAdminEmail;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            TokenStoreService tokenStoreService,
            @Value("${security.bootstrap-admin-email:}") String bootstrapAdminEmail
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenStoreService = tokenStoreService;
        this.bootstrapAdminEmail = bootstrapAdminEmail;
    }

    public UserResponse create(UserSignupRequest request) {
        UserRole role = request.email() != null && request.email().equalsIgnoreCase(bootstrapAdminEmail)
                ? UserRole.ADMIN : UserRole.USER;

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .birthDate(parseDate(request.birthDate()))
                .gender(request.gender())
                .nickname(request.nickname())
                .loginProvider(request.loginProvider())
                .providerId(request.providerId())
                .role(role)
                .build();

        return toResponse(userRepository.save(user));
    }

    public AuthResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
        return issueTokens(user);
    }

    public AuthResponse refresh(String refreshToken) {
        if (!jwtTokenProvider.isRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token type");
        }
        long userId = jwtTokenProvider.getUserId(refreshToken);
        String stored = tokenStoreService.getRefreshToken(userId);
        if (stored == null || !stored.equals(refreshToken)) {
            throw new IllegalArgumentException("Refresh token revoked or mismatched");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return issueTokens(user);
    }

    public void logout(String accessToken, String refreshToken) {
        if (accessToken != null && !accessToken.isBlank()) {
            tokenStoreService.blacklistAccessToken(accessToken, jwtTokenProvider.getRemainingValidityMillis(accessToken));
        }
        if (refreshToken != null && !refreshToken.isBlank()) {
            tokenStoreService.deleteRefreshToken(jwtTokenProvider.getUserId(refreshToken));
        }
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        return userRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toResponse)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public UserResponse updateRole(Long userId, UserRole role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setRole(role);
        return toResponse(userRepository.save(user));
    }

    public boolean existsByUsername(String username) { return userRepository.existsByUsername(username); }
    public boolean existsByEmail(String email) { return userRepository.existsByEmail(email); }
    public void delete(Long id) { userRepository.deleteById(id); }

    private AuthResponse issueTokens(User user) {
        String access = jwtTokenProvider.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        String refresh = jwtTokenProvider.generateRefreshToken(user.getId(), user.getEmail(), user.getRole().name());
        tokenStoreService.saveRefreshToken(user.getId(), refresh, jwtTokenProvider.getRefreshExpirationMillis());
        return new AuthResponse(access, refresh, "Bearer", toResponse(user));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getAddress(),
                user.getBirthDate(), user.getGender(), user.getNickname(), user.getLoginProvider(), user.getRole(),
                user.getCreatedAt(), user.getLastLoginAt());
    }

    private LocalDate parseDate(String value) {
        if (value == null || value.isBlank()) return null;
        return LocalDate.parse(value);
    }
}
