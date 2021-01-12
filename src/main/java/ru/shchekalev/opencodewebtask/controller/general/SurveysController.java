package ru.shchekalev.opencodewebtask.controller.general;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
public class SurveysController {

    private final SurveyService surveyService;
    private final UserService userService;

    public SurveysController(SurveyService surveyService, UserService userService) {
        this.surveyService = surveyService;
        this.userService = userService;
    }

    @GetMapping
    public String showAllSurveys(Model model,
                                 @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("surveys", surveyService.findAvailableAndNotStarted(userService.findByUsername(user.getUsername())));

        return "general/surveys";
    }
}
