package com.example.login_project_spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping(value = "/member/main")
  public String Main() {

    return "main/main";
  }
}
