package ru.shchekalev.opencodewebtask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.model.assistant.Availability;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.repository.SurveyRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.SurveyService;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Survey with id " + id + " doesn't exist"));
    }

    @Override
    public List<Survey> findAllAvailable() {
        return surveyRepository.findAllByStatus(Availability.AVAILABLE);
    }

    @Override
    public Survey update(Long id, Survey newSurvey) {
        Survey survey = surveyRepository.getOne(id);
        survey.setName(newSurvey.getName());

        return surveyRepository.save(survey);
    }
}
