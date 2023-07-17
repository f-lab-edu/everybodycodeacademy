package com.example.login_project_spring_boot_security.controller;

import com.example.login_project_spring_boot_security.member.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping(value = "/member/main")
  public String Main(Model model, Member member) {

    return "main/main";
  }
}
