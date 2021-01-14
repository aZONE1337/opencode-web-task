package ru.shchekalev.opencodewebtask.controller.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.services.interfaces.AnswerService;
import ru.shchekalev.opencodewebtask.services.interfaces.QuestionService;

@Controller
@RequestMapping("/constructor/answers")
public class AnswersConstructorController {

    QuestionService questionService;

    AnswerService answerService;

    @Autowired
    public AnswersConstructorController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping
    public String getQuestionsAnswers(@RequestParam("question_id") Long questionId,
                                      Model model) {
        model.addAttribute("question", questionService.findById(questionId));
        model.addAttribute("answers", answerService.findAllByQuestionId(questionId));

        return "constructor/questions_answers";
    }

    @PostMapping
    public String createNewAnswer(@RequestParam("question_id") Long questionId,
                                  @ModelAttribute Answer answer) {
        answer.setQuestion(questionService.findById(questionId));
        answerService.save(answer);

        return "redirect:/constructor/answers?question_id=" + questionId;
    }

    @GetMapping("/new")
    public String showNewAnswerPage(@RequestParam("question_id") Long questionId,
                                    Model model) {
        model.addAttribute("question", questionService.findById(questionId));
        model.addAttribute("answer", new Answer());

        return "constructor/new_answer";
    }

    @GetMapping("/{id}/edit")
    public String showEditQuestionPage(@PathVariable Long id,
                                       Model model) {
        model.addAttribute("answer", answerService.findById(id));

        return "constructor/edit_answer";
    }

    @PatchMapping("/{id}")
    public String editAnswer(@PathVariable Long id,
                             @ModelAttribute Answer newAnswer) {
        Answer answer = answerService.update(id, newAnswer);

        return "redirect:/constructor/answers?question_id=" + answer.getQuestion().getId();
    }
}
