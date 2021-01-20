package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;

public interface AnswerService {

    List<Answer> findAllByQuestionId(Long id);

    Answer save(Answer answer);

    Answer findById(Long id);

    Answer update(Long id, Answer newAnswer);
}
