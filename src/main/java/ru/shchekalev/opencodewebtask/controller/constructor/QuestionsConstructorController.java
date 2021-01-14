package ru.shchekalev.opencodewebtask.controller.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekalev.opencodewebtask.model.entity.Question;
import ru.shchekalev.opencodewebtask.services.interfaces.QuestionService;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;

@Controller
@RequestMapping("/constructor/questions")
public class QuestionsConstructorController {

    SurveyService surveyService;

    QuestionService questionService;

    @Autowired
    public QuestionsConstructorController(SurveyService surveyService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    @GetMapping
    public String getSurveysQuestions(@RequestParam("survey_id") Long surveyId,
                                        Model model) {
        model.addAttribute("survey", surveyService.findById(surveyId));
        model.addAttribute("questions", questionService.findAllBySurveyId(surveyId));

        return "constructor/surveys_questions";
    }

    @PostMapping
    public String createNewQuestion(@RequestParam("survey_id") Long surveyId,
                                    @ModelAttribute Question question) {
        question.setSurvey(surveyService.findById(surveyId));
        questionService.save(question);

        return "redirect:/constructor/questions?survey_id=" + surveyId;
    }

    @GetMapping("/new")
    public String showNewQuestionPage(@RequestParam("survey_id") Long surveyId,
                                      Model model) {
        model.addAttribute("survey", surveyService.findById(surveyId));
        model.addAttribute("question", new Question());

        return "constructor/new_question";
    }

    @GetMapping("/{id}/edit")
    public String showEditQuestionPage(@PathVariable Long id,
                                       Model model) {
        model.addAttribute("question", questionService.findById(id));

        return "constructor/edit_question";
    }

    @PatchMapping("/{id}")
    public String editQuestion(@PathVariable Long id,
                               @ModelAttribute Question newQuestion) {
        Question question = questionService.update(id, newQuestion);

        return "redirect:/constructor/questions?survey_id=" + question.getSurvey().getId();
    }
}
