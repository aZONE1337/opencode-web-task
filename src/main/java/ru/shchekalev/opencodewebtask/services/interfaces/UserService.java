package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    List<User> findAllByCompletedSurvey(Survey survey);

    User findById(Long id);

    void updateAnswers(Long id, Set<Answer> answers);

    void addCompletedSurvey(Long id, Survey survey);

    void removeCompletedSurvey(Long id, Survey survey);
}
