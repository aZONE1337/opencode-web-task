package ru.shchekalev.opencodewebtask.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekalev.opencodewebtask.model.security.Role;
import ru.shchekalev.opencodewebtask.model.entity.Question;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.services.interfaces.QuestionService;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

import java.util.List;

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

    @GetMapping
    public String redirectToSurveys() {
        return "redirect:/surveys";
    }

    @GetMapping("/surveys")
    public String getAvailableSurveys(@AuthenticationPrincipal UserDetails currUser,
                                      Model model) {
        User user = userService.findByUsername(currUser.getUsername());
        model.addAttribute("surveys", surveyService.findAllAvailable());
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

    @GetMapping("/in_process")
    public String getInProcessSurveys(@AuthenticationPrincipal UserDetails currUser,
                                      Model model) {
        //TODO... show not completed surveys and load progress to continue answering

        return "common/in_process";
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
         User userToUpd = userService.findByUsername(currUser.getUsername());

         userToUpd.getAnswers().addAll(user.getAnswers());
         userService.save(userToUpd);

         return "redirect:/surveys/" + surveyId + "?question=" + questionNum;
    }

    @PostMapping("/surveys/{id}/complete")
    public String saveUserProgress(@PathVariable("id") Long surveyId,
                                   @AuthenticationPrincipal UserDetails currUser) {
        User user = userService.findByUsername(currUser.getUsername());
        Survey survey = surveyService.findById(surveyId);

        user.getCompletedSurveys().add(survey);
        userService.save(user);

        return "redirect:/surveys";
    }
}
