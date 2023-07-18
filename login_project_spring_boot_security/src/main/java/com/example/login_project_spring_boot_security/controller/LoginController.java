package com.example.login_project_spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  @RequestMapping(value = "/member/login/loginForm")
  public String Login() {

    return "login/login";
  }
}
