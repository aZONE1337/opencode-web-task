package ru.shchekalev.opencodewebtask.services.interfaces;

import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.models.Survey;
import ru.shchekalev.opencodewebtask.models.User;

import java.util.List;

public interface SurveyService {

    void create(Survey survey);

    List<Survey> findAllSurveys();

    Survey findSurveyById(Long surveyId);

    List<Survey> findSurveysByUser(User user);

    List<Survey> findAllNotStarted(User user);

    List<Survey> findAvailableAndNotStarted(User user);
}
