package org.fd.controller;

import org.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 */
@Controller
public class HomeController
{
  @Autowired
  private UserService service;

  /**
   * Displays the home page.
   */
  @RequestMapping("/")
  public String home(final Model model)
  {
    model.addAttribute("users", service.list());

    return "home";
  }
}
