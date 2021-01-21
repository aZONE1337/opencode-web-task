package ru.shchekalev.opencodewebtask.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.Question;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.model.security.Role;
import ru.shchekalev.opencodewebtask.services.interfaces.QuestionService;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class SurveysCommonController {

    SurveyService surveyService;

    UserService userService;

    QuestionService questionService;

    @Autowired
    public SurveysCommonController(SurveyService surveyService, UserService userService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping("/surveys")
    public String getAvailableAndNotCompletedSurveys(@AuthenticationPrincipal UserDetails currUser,
                                      Model model) {
        User user = userService.findByUsername(currUser.getUsername());

        model.addAttribute("surveys", surveyService.findAllAvailableAndNotCompletedByUser(user));
        model.addAttribute("admin", user.getRole() == Role.ADMIN);

        return "common/available_surveys";
    }

    @GetMapping("/completed")
    public String getCompletedSurveys(@AuthenticationPrincipal UserDetails currUser,
                                      Model model) {
        User user = userService.findByUsername(currUser.getUsername());
        List<Survey> completedSurveys = surveyService.findAllCompletedByUser(
                userService.findByUsername(currUser.getUsername())
        );

        model.addAttribute("surveys", completedSurveys);
        model.addAttribute("admin", user.getRole() == Role.ADMIN);

        return "common/completed";
    }

    @GetMapping("/completed/{id}")
    public String getCompletedSurvey(@PathVariable("id") Long surveyId,
                                     @AuthenticationPrincipal UserDetails currUser,
                                     Model model) {
        User user = userService.findByUsername(currUser.getUsername());
        Survey survey = surveyService.findById(surveyId);

        model.addAttribute("survey", survey);
        model.addAttribute("user", user);
        model.addAttribute("admin", user.getRole() == Role.ADMIN);

        return "common/completed_survey";
    }

    @PatchMapping("/completed/{id}")
    public String resetSurvey(@PathVariable("id") Long surveyId,
                              @AuthenticationPrincipal UserDetails currUser) {
        User user = userService.findByUsername(currUser.getUsername());
        Survey survey = surveyService.findById(surveyId);

        userService.removeCompletedSurvey(user.getId(), survey);

        return "redirect:/completed";
    }

    @GetMapping("/surveys/{id}")
    public String getSurveysQuestion(@PathVariable("id") Long surveyId,
                                     @RequestParam("question") int questionNum,
                                     @AuthenticationPrincipal UserDetails currUser,
                                     Model model) {
        User user = userService.findByUsername(currUser.getUsername());
        Survey survey = surveyService.findById(surveyId);
        Question question = survey.getQuestions().get(questionNum);

        model.addAttribute("survey", survey);
        model.addAttribute("question", question);
        model.addAttribute("questionNum", questionNum);
        model.addAttribute("user", new User());
        model.addAttribute("admin", user.getRole() == Role.ADMIN);

        return "common/survey_question";
    }

    @PostMapping("/surveys/{id}")
    public String saveUserAnswer(@PathVariable("id") Long surveyId,
                                 @RequestParam("question") int questionNum,
                                 @AuthenticationPrincipal UserDetails currUser,
                                 @ModelAttribute User user) {
        Survey survey = surveyService.findById(surveyId);
        Long userId = userService.findByUsername(currUser.getUsername()).getId();
        Set<Answer> newAnswers = user.getAnswers();

        userService.updateAnswers(userId, newAnswers);

        if (survey.getQuestions().size() == ++questionNum) {
            userService.addCompletedSurvey(userId, survey);

            return "redirect:/surveys";
        }

        return "redirect:/surveys/" + surveyId + "?question=" + questionNum;
    }
}
