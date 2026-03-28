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

    @GetMapping(value = "/users")
    public String users(ModelMap model) {
        List<User> users = userService.getUsers();

        model.addAttribute("users", users);
        model.addAttribute("user", UserService.createUser());
        model.addAttribute("allRoles", userService.getAllRoles());

        return "users";
    }

    @GetMapping(value = "/userList")
    public String userList(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("usersCount", "There are " + users.size() + " users stored in the DB");
        model.addAttribute("users", users);

        return "userlist";
    }

    @GetMapping(value = "/adduser")
    public String addUser(ModelMap model) {
        model.addAttribute("user", UserService.createUser());
        model.addAttribute("allRoles", userService.getAllRoles());

        return "adduser";
    }

    @PostMapping(value = "/saveuser")
    public String saveUser(@ModelAttribute("user") User user, ModelMap model,
                           @RequestParam(value = "roles", required = false) List<String> roleNames) {

        userService.assignRoles(user, roleNames);
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

    @GetMapping("/removeuser")
    public String removeUser(@RequestParam("id") Long id, ModelMap model) {
        userService.removeUserById(id);

        return "redirect:/users";
    }

    @GetMapping("/user")
    public String userProfile(Model model) {
        User user = userService.findCurrentUser();

        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getAllRoles());
        return "user";
    }


}
