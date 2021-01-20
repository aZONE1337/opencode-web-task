package ru.shchekalev.opencodewebtask.controller.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/users_results")
public class ResultsOverviewController {

    UserService userService;

    SurveyService surveyService;

    @Autowired
    public ResultsOverviewController(UserService userService, SurveyService surveyService) {
        this.userService = userService;
        this.surveyService = surveyService;
    }

    @GetMapping
    public String getAnsweredSurveys(Model model) {
        model.addAttribute("surveys", surveyService.findAllAvailable());

        return "result/answered_surveys";
    }

    @GetMapping("/{id}")
    public String getAnsweredUsers(@PathVariable("id") Long surveyId,
                                   Model model) {
        Survey survey = surveyService.findById(surveyId);

        model.addAttribute("answeredUsers", userService.findAllByCompletedSurveys(survey));
        model.addAttribute("surveyId", surveyId);

        return "result/answered_users";
    }

    @GetMapping("/user/{id}")
    public String getExactUserAnswers(@PathVariable("id") Long userId,
                                      @RequestParam("survey") Long surveyId,
                                      Model model) {
        model.addAttribute("survey", surveyService.findById(surveyId));
        model.addAttribute("user", userService.findById(userId));

        return "result/survey_answers";
    }
}
