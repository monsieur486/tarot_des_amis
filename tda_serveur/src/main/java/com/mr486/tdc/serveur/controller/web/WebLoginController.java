package com.mr486.tdc.serveur.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebLoginController {

  @GetMapping(value = { "/login" })
  public String getLoginPage()
  {
    return "login";
  }

}
