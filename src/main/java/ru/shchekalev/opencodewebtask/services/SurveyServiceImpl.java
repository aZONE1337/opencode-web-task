package ru.shchekalev.opencodewebtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.models.*;
import ru.shchekalev.opencodewebtask.repositories.SurveyRepository;
import ru.shchekalev.opencodewebtask.repositories.UserSurveyRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserSurveyRepository userSurveyRepository;;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, UserSurveyRepository userSurveyRepository) {
        this.surveyRepository = surveyRepository;
        this.userSurveyRepository = userSurveyRepository;
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
    public List<Survey> findSurveysByUser(User user) {
        return surveyRepository.findAllByAuthor(user);
    }

    @Override
    public List<Survey> findAllNotStarted(User user) {
        List<UserSurvey> rawData = userSurveyRepository.findAll();

        return rawData.stream()
                .filter(userSurvey ->
                        userSurvey.getAnswerStatus() == AnswerStatus.NOT_STARTED &&
                                userSurvey.getUserSurveyPK().getUser().getId().equals(user.getId()))
                .map(userSurvey -> userSurvey.getUserSurveyPK().getSurvey())
                .collect(Collectors.toList());
    }

    @Override
    public List<Survey> findAvailableAndNotStarted(User user) {
        List<Survey> rawData = findAllNotStarted(user);

        return rawData.stream()
                .filter(survey -> survey.getStatus() == Availability.AVAILABLE)
                .collect(Collectors.toList());
    }
}
