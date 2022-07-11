package com.mr486.tdc.serveur.controller.web;

import com.mr486.tdc.serveur.model.Copain;
import com.mr486.tdc.serveur.service.CopainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@Controller
public class WebCopainsController {

  private final CopainService copainService;

  public WebCopainsController(CopainService copainService) {
    this.copainService = copainService;
  }

  @GetMapping(value = { "/copains" })
  public String getHomePage(Model model){
    List<Copain> copains = copainService.toutLesCopains();
    model.addAttribute("copains", copains);
    return "copains";
  }
}
