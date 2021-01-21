package ru.shchekalev.opencodewebtask.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.model.security.Role;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@RequestMapping("/")
public class AuthController {

    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getHomePage(@AuthenticationPrincipal UserDetails currUser,
                              Model model) {
        User user = userService.findByUsername(currUser.getUsername());

        model.addAttribute("admin", user.getRole() == Role.ADMIN);

        return "index";
    }

    @GetMapping("/login-error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("error", true);

        return "auth/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", false);

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute User user,
                                  Model model) {
        if (userService.findByUsername(user.getUsername()) == null) {
            userService.save(user);

            return "redirect:/login";
        }

        model.addAttribute("error", true);

        return "auth/registration";
    }
}
