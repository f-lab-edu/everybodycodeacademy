package flab.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import flab.userservice.dto.UserLoginRequest;
import flab.userservice.dto.UserSignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공 시 201 반환")
    void signupSuccess() throws Exception {
        var req = new UserSignupRequest("testuser", "test@example.com", "password123");
        mockMvc.perform(post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("회원가입 유효성 실패 시 400 반환")
    void signupValidationError() throws Exception {
        // email을 빈 값으로
        var req = new UserSignupRequest("testuser", "", "password123");
        mockMvc.perform(post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("로그인 성공 시 200 반환")
    void loginSuccess() throws Exception {
        var req = new UserLoginRequest("test@example.com", "password123");
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패(인증 오류) 시 401 반환")
    void loginUnauthorized() throws Exception {
        var req = new UserLoginRequest("wrong@example.com", "badpass");
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isUnauthorized());
    }
}