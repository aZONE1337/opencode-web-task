package ru.shchekalev.opencodewebtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekalev.opencodewebtask.models.Survey;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@RequestMapping("/surveys")
public class SurveysController {

    private final SurveyService surveyService;
    private UserService userService;

    @Autowired
    public SurveysController(SurveyService surveyService, UserService userService) {
        this.surveyService = surveyService;
        this.userService = userService;
    }

    @GetMapping
    public String showSurveys(Model model,
                              @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("surveys", surveyService.findSurveysByUser(userService.findByUsername(user.getUsername())));

        return "surveys";
    }

    @GetMapping("/{id}")
    public String showSurvey(@PathVariable("id") Long surveyId,
                            @RequestParam("question") Long questionId,
                            Model model) {


        return null;
    }
}
