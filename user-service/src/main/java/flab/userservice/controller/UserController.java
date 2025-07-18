package flab.userservice.controller;


import flab.userservice.dto.UserSignupRequest;
import flab.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import flab.userservice.dto.UserSignupRequest;
import flab.userservice.dto.UserLoginResquest;
import flab.userservice.dto.UserResponse;

import flab.common.api.ApiResponse;


@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> signup(@RequestBody UserSignupRequest request) {



        userService.userSignUp(request);

        HttpHeaders headers = new HttpHeaders();
        ApiResponse response = new ApiResponse("회원가입 성공", 201);
        headers.add("Message", response.getMessage());
        headers.add("Status", String.valueOf(response.getStatus()));
        log.info("회원가입 성공");

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void login(@RequestBody UserLoginResquest request) {
        //userService.login(request);
        //return "OK";
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String logout() {
        //userService.logout();
        return "LogOut";
    }
}