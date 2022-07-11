package com.mr486.tdc.serveur.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import static com.mr486.tdc.serveur.TdcMainsrvApplication.chatServer;


@Controller
public class WebCagnottesController {

  @GetMapping(value = { "/cagnottes" })
  public String getHomePage(Model model)
  {
    Integer content = 100;
    chatServer.sendToAll(200, content);
    return "cagnottes";
  }

}
