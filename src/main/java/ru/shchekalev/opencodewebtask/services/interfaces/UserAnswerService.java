package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;

public interface UserAnswerService {

    List<Answer> findUsersAnswers(User user);
}
