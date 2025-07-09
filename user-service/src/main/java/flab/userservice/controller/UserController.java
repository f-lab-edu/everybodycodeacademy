package flab.userservice.controller;


import flab.userservice.dto.UserSignupRequest;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Object> signup(@RequestBody UserSignupRequest request) {


        try{
            if(
                true // 임시 설정
                //userService.signup(request)){
            ){
                //성공시
                log.info("회원가입 성공");
                HttpHeaders headers = new HttpHeaders();
                ApiResponse response = new ApiResponse("User registration success", 201);
                headers.add("Message", response.getMessage().toString());
                headers.add("Status", String.valueOf(response.getStatus()));

                return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
            }else{//실패시
                log.info("회원가입 실패");
                HttpHeaders headers = new HttpHeaders();
                ApiResponse response = new ApiResponse("User registration failed", 209);
                headers.add("Message", response.getMessage());
                headers.add("Status", String.valueOf(response.getStatus()));

                return new ResponseEntity<>(response, headers, HttpStatus.CONFLICT);
            }
        }catch(Exception exception){
            log.error("서버 에러");
            HttpHeaders headers = new HttpHeaders();
            ApiResponse response = new ApiResponse("Server Internal Error", 500);
            headers.add("Message", response.getMessage());
            headers.add("Status", String.valueOf(response.getStatus()));

            return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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