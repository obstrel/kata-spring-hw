package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/adduser")
    public String addUser(ModelMap model) {
        model.addAttribute("user", UserService.createUser());
        model.addAttribute("allRoles", userService.getAllRoles()); // Передаем все доступные роли

        return "adduser";
    }

    @PostMapping(value = "/saveuser")
    public String saveUser(@ModelAttribute("user") User user, ModelMap model,
                           @RequestParam(value = "roleIds", required = false) List<Long> roleIds) {

        if (roleIds != null && !roleIds.isEmpty()) {
            Set<Role> roles = roleIds.stream()
                    .map(roleService::findById)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        userService.saveUser(user);

        model.addAttribute("successMessage", "User " + user.getLastName() + " stored success!");

        return "saveuser";
    }

    @GetMapping("/edituser")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getAllRoles()); 

        return "edituser";
    }


}
