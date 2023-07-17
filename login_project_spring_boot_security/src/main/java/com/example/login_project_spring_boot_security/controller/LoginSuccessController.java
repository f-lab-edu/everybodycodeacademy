package com.example.login_project_spring_boot_security.controller;

import com.example.login_project_spring_boot_security.security.MemberPrincipalDetails;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginSuccessController {

  @RequestMapping(value = "/member/text")
  public String LoginSuccess(Model model, Principal principal) {

    MemberPrincipalDetails memberDetails =
        (MemberPrincipalDetails) ((Authentication) principal).getPrincipal();

    model.addAttribute("member", memberDetails.getMember());

    return "login/text";
  }
}
