package ru.shchekalev.opencodewebtask.controller.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserService;

@Controller
@RequestMapping("/constructor/surveys")
public class SurveysConstructorController {

    SurveyService surveyService;

    UserService userService;

    @Autowired
    public SurveysConstructorController(SurveyService surveyService, UserService userService) {
        this.surveyService = surveyService;
        this.userService = userService;
    }

    @GetMapping
    public String getSurveys(Model model) {
        model.addAttribute("surveys", surveyService.findAll());

        return "constructor/surveys";
    }

    @PostMapping
    public String createNewSurvey(@AuthenticationPrincipal UserDetails user,
                                  @ModelAttribute Survey survey) {
        survey.setAuthor(userService.findByUsername(user.getUsername()));
        surveyService.save(survey);

        return "redirect:/constructor/surveys";
    }

//    @GetMapping("/{id}")
//    public String getSurvey(@PathVariable Long id,
//                            Model model) {
//        model.addAttribute("survey", surveyService.findById(id));
//
//        return "constructor/survey";
//    }

    @GetMapping("/new")
    public String showNewSurveyPage(Model model) {
        model.addAttribute("survey", new Survey());

        return "constructor/new_survey";
    }

    @GetMapping("/{id}/edit")
    public String showEditSurveyPage(@PathVariable Long id,
                                     Model model) {
        Survey survey = surveyService.findById(id);
        model.addAttribute("survey", survey);
        model.addAttribute("valid", survey.isValid());

        return "constructor/edit_survey";
    }

    @PatchMapping("/{id}")
    public String editSurvey(@PathVariable Long id,
                             @ModelAttribute Survey newSurvey) {
        surveyService.update(id, newSurvey);

        return "redirect:/constructor/surveys";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteSurvey(@PathVariable("id") Long surveyId,
                               @AuthenticationPrincipal UserDetails currUser) {
        Survey survey = surveyService.findById(surveyId);
        User user = userService.findByUsername(currUser.getUsername());

        user.getCompletedSurveys().remove(survey);

        surveyService.deleteSurveyById(surveyId);

        return "redirect:/constructor/surveys";
    }
}
