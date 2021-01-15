package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    List<User> findAllByCompletedSurveys(Survey survey);

    User findById(Long id);
}
