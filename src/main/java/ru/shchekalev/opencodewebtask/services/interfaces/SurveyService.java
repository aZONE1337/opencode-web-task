package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;

import java.util.List;

public interface SurveyService {

    Survey save(Survey survey);

    List<Survey> findAll();

    Survey findById(Long id);

    List<Survey> findAllAvailable();

    Survey update(Long id, Survey newSurvey);

    List<Survey> findAllCompletedByUser(User user);

    List<Survey> findAllAvailableAndNotCompletedByUser(User user);
}
