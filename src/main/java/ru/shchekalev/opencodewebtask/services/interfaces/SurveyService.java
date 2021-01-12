package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;

public interface SurveyService {

    void create(Survey survey);

    List<Survey> findAllSurveys();

    Survey findSurveyById(Long surveyId);

    List<Survey> findAvailableAndNotStarted(User user);
}
