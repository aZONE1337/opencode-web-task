package ru.shchekalev.opencodewebtask.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.model.assistant.AnswerStatus;
import ru.shchekalev.opencodewebtask.model.entity.Survey;
import ru.shchekalev.opencodewebtask.model.entity.User;
import ru.shchekalev.opencodewebtask.model.entity.UserSurvey;
import ru.shchekalev.opencodewebtask.repository.UserSurveyRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.UserSurveyService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserSurveyImpl implements UserSurveyService {

    private final UserSurveyRepository userSurveyRepository;

    @Autowired
    public UserSurveyImpl(UserSurveyRepository userSurveyRepository) {
        this.userSurveyRepository = userSurveyRepository;
    }

    @Override
    public List<Survey> findAllNotStarted(User user) {
        List<UserSurvey> unfiltered = userSurveyRepository.findAll();

        return unfiltered.stream()
                .filter(userSurvey ->
                        userSurvey.getAnswerStatus() == AnswerStatus.NOT_STARTED &&
                                userSurvey.getUserSurveyPK().getUser().getId().equals(user.getId()))
                .map(userSurvey -> userSurvey.getUserSurveyPK().getSurvey())
                .collect(Collectors.toList());
    }
}
