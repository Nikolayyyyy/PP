package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String listUsers(Model model) {
        List<User> userList = userService.listUsers();
        model.addAttribute("listUsers", userList);
        return "users-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "/user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("/user-delete")
    public String deleteUser(int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping(value = "/user-update")
    public String updateUserForm(int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping(value = "/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
