package ru.shchekalev.opencodewebtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shchekalev.opencodewebtask.models.Survey;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@RequestMapping("/constructor/surveys")
public class SurveyConstructorController {

    private final SurveyService surveyService;
    private final UserService userService;

    @Autowired
    public SurveyConstructorController(SurveyService surveyService, UserService userService) {
        this.surveyService = surveyService;
        this.userService = userService;
    }

    @GetMapping
    public String showSurveys(Model model) {
        model.addAttribute("surveys", surveyService.findAllSurveys());

        return "constructor/surveys";
    }

    @GetMapping("/create")
    public String showSurveyCreator(Model model) {
        model.addAttribute("survey", new Survey());

        return "constructor/create_survey";
    }

    @PostMapping("/create")
    public String createSurvey(@ModelAttribute Survey survey,
                               @AuthenticationPrincipal UserDetails user) {
        survey.setAuthor(userService.findByUsername(user.getUsername()));
        surveyService.create(survey);

        return "redirect:/constructor/surveys";
    }
}
