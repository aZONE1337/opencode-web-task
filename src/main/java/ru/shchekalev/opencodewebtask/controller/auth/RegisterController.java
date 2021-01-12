package ru.shchekalev.opencodewebtask.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());

        return "auth/registration";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.create(user);

        return "redirect:/login";
    }
}
