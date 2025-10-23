package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String users(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("usersCount", "There are " + users.size() + " users stored in the DB");
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping(value = "/adduser")
    public String addUser(ModelMap model) {
        model.addAttribute("user", UserService.createUser());

        return "adduser";
    }

    @GetMapping("/edituser")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);

        model.addAttribute("user", user);
        return "edituser";
    }

    @GetMapping("/removeuser")
    public String removeUser(@RequestParam("id") Long id, ModelMap model) {
        userService.removeUserById(id);

        return users(model);
    }

    @PostMapping(value = "/saveuser")
    public String saveUser(@ModelAttribute("user") User user, ModelMap model) {
        userService.saveUser(user);

        model.addAttribute("successMessage", "User " + user.getLastName() + " stored success!");

        return "saveuser";
    }
}
