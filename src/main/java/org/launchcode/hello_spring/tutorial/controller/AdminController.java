package org.launchcode.hello_spring.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("title", "Admin");
        return "tutorial/admin";
    }
}

