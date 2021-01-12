package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Question;

import java.util.List;

public interface QuestionService {

    Question create(Question question);

    List<Question> findSurveysQuestions(Long surveyId);
}
