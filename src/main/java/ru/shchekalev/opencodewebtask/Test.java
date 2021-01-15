package ru.shchekalev.opencodewebtask;

import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.Question;
import ru.shchekalev.opencodewebtask.model.security.Role;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println(Role.ADMIN.name());
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Question question = new Question();
//        question.setAnswers(answers);
        System.out.println(question.isValid());
    }
}
