package ru.shchekalev.opencodewebtask.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;

@Controller
@RequestMapping("/")
public class SurveysGeneralController {

    @Autowired
    SurveyService surveyService;

    @GetMapping
    public String showWelcomePage() {
        return "index";
    }

    @GetMapping("/surveys")
    public String getAvailableSurveys(Model model) {
        model.addAttribute("surveys", surveyService.findAllAvailable());

        return "general/surveys";
    }

    @GetMapping("completed")
    public String getCompletedSurveys(Model model) {
        return "general/completed";
    }
}
