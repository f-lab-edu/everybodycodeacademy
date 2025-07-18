package flab.userservice.controller;


import flab.common.api.ApiResponse;
import flab.userservice.dto.UserSignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerRandomPortUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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

        //테스트 url + port + 컨트롤러 end point
        String url = "http://localhost:" + port + "/users/signup";

        ResponseEntity<ApiResponse> response = restTemplate.postForEntity(url,request, ApiResponse.class);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders());
        assertEquals("User registration success", response.getHeaders().get("Message").get(0));
        assertEquals("201", response.getHeaders().get("Status").get(0));
    }
}
