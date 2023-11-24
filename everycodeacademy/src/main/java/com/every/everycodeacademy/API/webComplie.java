package com.every.everycodeacademy.API;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class webComplie {

  @GetMapping ("/webcomplie")
  public String webCompliePage() {
    return "webcomplie/webcomplieinnput";
  }
}
