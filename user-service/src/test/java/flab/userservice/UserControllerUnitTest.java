package flab.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;

import flab.userservice.controller.UserController;
import flab.userservice.dto.UserSignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 컨트롤러가 의존하는 서비스 레이어를 모킹
    //@MockBean
//    private UserService userService;

    @Test
    @DisplayName("회원가입 요청이 들어오면 서비스 호출 후 201 반환")
    void signupCallsService() throws Exception {
        // 서비스가 정상적으로 처리했다고 가정
        UserSignupRequest request = new UserSignupRequest(
                "john_doe",
                "john.doe@example.com",
                "securePassword123",
                "01012345678",
                "123 Main Street", "1990-01-01",
                "MALE",
                "johnny",
                "LOCAL",
                null
        );

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User registration success"))
                .andExpect(jsonPath("$.status").value(201));

    }

    @Test
    @DisplayName("로그인 요청이 들어오면 서비스 호출 후 200 반환")
    void loginCallsService() throws Exception {
        // 서비스가 토큰 객체를 반환한다고 가정
       /* Mockito.when(userService.login(any(UserLoginRequest.class)))
            .thenReturn(/* 예: new UserLoginResponse("at", "rt")  null);

        var req = new UserLoginRequest("u@example.com", "pass");
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isOk());

        Mockito.verify(userService).login(any(UserLoginRequest.class));
     */
    }
}