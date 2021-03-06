package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Question;

import java.util.List;

public interface QuestionService {

    Question save(Question question);

    List<Question> findAllBySurveyId(Long id);

    Question findById(Long id);

    Question update(Long id, Question newQuestion);
}
