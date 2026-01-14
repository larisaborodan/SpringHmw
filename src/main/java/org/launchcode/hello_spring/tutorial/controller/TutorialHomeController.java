package org.launchcode.hello_spring.tutorial.controller;

import lombok.RequiredArgsConstructor;
import org.launchcode.hello_spring.models.RegistrationRequest;
import org.launchcode.hello_spring.models.dto.UserDto;
import org.launchcode.hello_spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TutorialHomeController {

    private final UserService userService;

    @GetMapping("/tutorial")
    public String home(Model model, Authentication authentication){
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Home");

        return "tutorial/index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login");

        return "tutorial/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("loginError", true);

        return "tutorial/login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(value="registrationSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("registrationSuccess", success);
        model.addAttribute("user", new RegistrationRequest());

        return "tutorial/register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest,
                             RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(registrationRequest);
            // Folosim flash attribute, nu în URL
            redirectAttributes.addFlashAttribute("registrationSuccess", "Success");
        } catch (Exception e) {
            // dacă apare eroare (ex: email duplicat)
            redirectAttributes.addFlashAttribute("registrationSuccess", "Failed");
        }

        // Redirect simplu fără să pui mesajul în URL
        return "redirect:/register";
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/tutorial";
    }



}
