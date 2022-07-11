package com.mr486.tdc.serveur.controller.web;

import com.mr486.tdc.serveur.TdcMainsrvApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebIndexController {

  @GetMapping(value = { "/" })
  public String getHomePage(Model model)
  {
    model.addAttribute("reunionActiveId", TdcMainsrvApplication.idPartieActive());
    return "index";
  }

}
