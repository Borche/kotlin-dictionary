/* Copyright © 2021 */
package com.ab.ploy.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class IndexController {

  @GetMapping
  public String index(Model model) {
    log.info("Hello");
    return "index";
  }
}
