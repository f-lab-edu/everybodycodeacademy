package com.everycodeacademy.backend.security;

import com.everycodeacademy.backend.user.entity.User;
import com.everycodeacademy.backend.user.entity.UserRole;
import com.everycodeacademy.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class JwtSecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private TokenStoreService tokenStoreService;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userRepository.save(User.builder()
                .username("normal_user")
                .email("user@test.com")
                .password(passwordEncoder.encode("password123"))
                .loginProvider("LOCAL")
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .build());

        userRepository.save(User.builder()
                .username("admin_user")
                .email("admin@everycodeacademy.com")
                .password(passwordEncoder.encode("password123"))
                .loginProvider("LOCAL")
                .role(UserRole.ADMIN)
                .createdAt(LocalDateTime.now())
                .build());

        when(tokenStoreService.isBlacklisted(anyString())).thenReturn(false);
    }

    @Test
    @DisplayName("Protected endpoint requires token")
    void requiresToken() throws Exception {
        mockMvc.perform(get("/api/users/me")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Access token allows protected endpoint")
    void accessTokenWorks() throws Exception {
        Long userId = userRepository.findByEmail("user@test.com").orElseThrow().getId();
        String token = jwtTokenProvider.generateAccessToken(userId, "user@test.com", "USER");
        mockMvc.perform(get("/api/users/me").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Admin endpoint blocks USER and allows ADMIN")
    void roleBasedControl() throws Exception {
        Long userId = userRepository.findByEmail("user@test.com").orElseThrow().getId();
        Long adminId = userRepository.findByEmail("admin@everycodeacademy.com").orElseThrow().getId();

        String userToken = jwtTokenProvider.generateAccessToken(userId, "user@test.com", "USER");
        mockMvc.perform(patch("/api/users/{id}/role", userId)
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"ADMIN\"}"))
                .andExpect(status().isForbidden());

        String adminToken = jwtTokenProvider.generateAccessToken(adminId, "admin@everycodeacademy.com", "ADMIN");
        mockMvc.perform(patch("/api/users/{id}/role", userId)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"ADMIN\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Refresh endpoint issues new tokens")
    void refreshWorks() throws Exception {
        var loginResult = mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@test.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String body = loginResult.getResponse().getContentAsString();
        String refreshToken = body.split("\"refreshToken\":\"")[1].split("\"")[0];
        long userId = jwtTokenProvider.getUserId(refreshToken);
        when(tokenStoreService.getRefreshToken(userId)).thenReturn(refreshToken);

        mockMvc.perform(post("/api/users/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"refreshToken\":\"" + refreshToken + "\"}"))
                .andExpect(status().isOk());
    }
}
