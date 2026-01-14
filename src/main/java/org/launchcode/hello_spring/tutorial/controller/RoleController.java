package org.launchcode.hello_spring.tutorial.controller;

import lombok.RequiredArgsConstructor;
import org.launchcode.hello_spring.models.dto.RoleDto;
import org.launchcode.hello_spring.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/roles")
    public String getRoles(Model model){
        List<RoleDto> roles = roleService.getAllRoles();

        model.addAttribute("title", "Roles");
        model.addAttribute("roles", roles);

        return "tutorial/roles";
    }

    @GetMapping("/roles/{id}")
    public RoleDto getRoleById(@PathVariable Integer id){
        return roleService.getRoleById(id);
    }
}