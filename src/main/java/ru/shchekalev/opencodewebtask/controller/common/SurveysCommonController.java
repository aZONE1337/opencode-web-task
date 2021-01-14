package ru.shchekalev.opencodewebtask.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String getAvailableSurveys(Model model) {
        model.addAttribute("surveys", surveyService.findAllAvailable());

        return "common/available_surveys";
    }

    @GetMapping("/completed")
    public String getCompletedSurveys(@AuthenticationPrincipal UserDetails currUser,
                                      Model model) {
        List<Survey> completedSurveys = surveyService.findAllCompletedByUser(
                userService.findByUsername(currUser.getUsername())
        );

        model.addAttribute("surveys", completedSurveys);

        return "common/completed";
    }

    @GetMapping("/completed/{id}")
    public String getCompletedSurvey(@PathVariable("id") Long surveyId,
                                     @AuthenticationPrincipal UserDetails currUser,
                                     Model model) {
        model.addAttribute("survey", surveyService.findById(surveyId));
        model.addAttribute("user", userService.findByUsername(currUser.getUsername()));

        return "common/completed_survey";
    }

    @GetMapping("/in_process")
    public String getInProcessSurveys(@AuthenticationPrincipal UserDetails currUser,
                                      Model model) {
        //TODO show not completed surveys and load progress to continue answering

        return "common/in_process";
    }

    @GetMapping("/surveys/{id}")
    public String getSurveysQuestion(@PathVariable("id") Long surveyId,
                                     @RequestParam("question") int questionNum,
                                     Model model) {
        Survey survey = surveyService.findById(surveyId);
        Question question = survey.getQuestions().get(questionNum);

        model.addAttribute("survey", survey);
        model.addAttribute("question", question);
        model.addAttribute("questionNum", questionNum);
        model.addAttribute("user", new User());

        return "common/survey_question";
    }

    @PostMapping("/surveys/{id}")
    public String saveUserAnswer(@PathVariable("id") Long surveyId,
                                 @RequestParam("question") int questionNum,
                                 @AuthenticationPrincipal UserDetails user,
                                 @ModelAttribute User user1) {
         User userToUpd = userService.findByUsername(user.getUsername());
         userToUpd.getAnswers().addAll(user1.getAnswers());
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
