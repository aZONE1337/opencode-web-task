package ru.shchekalev.opencodewebtask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.model.assistant.Availability;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.repository.SurveyRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;
import ru.shchekalev.opencodewebtask.services.interfaces.UserSurveyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserSurveyService userSurveyService;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, UserSurveyService userSurveyService) {
        this.surveyRepository = surveyRepository;
        this.userSurveyService = userSurveyService;
    }

    @Override
    public void create(Survey survey) {
        surveyRepository.save(survey);
    }

    @Override
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey findSurveyById(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new NullPointerException("Survey with id " + surveyId + " doesn't exist"));
    }

    @Override
    public List<Survey> findAvailableAndNotStarted(User user) {
        List<Survey> surveysNotStarted = userSurveyService.findAllNotStarted(user);

        return surveysNotStarted.stream()
                .filter(survey -> survey.getStatus() == Availability.AVAILABLE)
                .collect(Collectors.toList());
    }
}
