package ru.shchekalev.opencodewebtask.controller.constructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.services.interfaces.AnswerService;
import ru.shchekalev.opencodewebtask.services.interfaces.QuestionService;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@RequestMapping("/constructor")
public class SurveyConstructorController {

    private final UserService userService;

    private final SurveyService surveyService;

    private final QuestionService questionService;

    private final AnswerService answerService;

    public SurveyConstructorController(UserService userService, SurveyService surveyService, QuestionService questionService, AnswerService answerService) {
        this.userService = userService;
        this.surveyService = surveyService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping
    public String showAllSurveys(Model model) {
        model.addAttribute("surveys", surveyService.findAllSurveys());

        return "general/surveys";
    }

    @GetMapping("/create")
    public String showSurveyCreationPage(Model model) {
        model.addAttribute("survey", new Survey());

        return "constructor/create_survey";
    }

    @PostMapping("/create")
    public String createSurvey(@ModelAttribute Survey survey,
                               @AuthenticationPrincipal UserDetails user) {
        survey.setAuthor(userService.findByUsername(user.getUsername()));
        surveyService.create(survey);

        return "constructor/create_survey";
    }
}
