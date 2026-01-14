package org.launchcode.hello_spring.tutorial.controller;

import lombok.RequiredArgsConstructor;
import org.launchcode.hello_spring.models.dto.UserDto;
import org.launchcode.hello_spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user")
    public String userHome() {
        return "user/index";
    }


    @GetMapping("/users")
    public String getUsers(Model model){
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "tutorial/users";
    }


    @GetMapping("/users/{id}")
    @ResponseBody
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }
}
