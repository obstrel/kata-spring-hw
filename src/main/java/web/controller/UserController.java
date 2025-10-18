package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public String users(ModelMap model) {
        model.addAttribute("usersCount", userService.getUsers().size());

        return "users";
    }

    @GetMapping(value = "/adduser")
    public String addUser(ModelMap model) {
        //model.addAttribute("usersCount", userService.getUsers().size());

        return "adduser";
    }
}
