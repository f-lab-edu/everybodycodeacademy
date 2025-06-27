package flab.userservice.controller;

import flab.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import flab.userservice.dto.UserLoginRequest;
import flab.userservice.dto.UserSignupRequest;


@Controller
@RequestMapping("/users")
public class UserController {

    /*
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    */
    
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String signup(@RequestBody UserSignupRequest request) {
        //userService.signup(request);
        return "CREATED";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void login(@RequestBody UserLoginRequest request) {
        //userService.login(request);
        return "OK";
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String logout() {
        //userService.logout();
        return "LogOut";
    }
}