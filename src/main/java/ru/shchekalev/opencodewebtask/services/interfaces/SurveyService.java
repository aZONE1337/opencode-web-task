package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Survey;

import java.util.List;

public interface SurveyService {

    Survey save(Survey survey);

    List<Survey> findAll();

    Survey findById(Long id);

    List<Survey> findAllAvailable();

    Survey update(Long id, Survey newSurvey);
}
