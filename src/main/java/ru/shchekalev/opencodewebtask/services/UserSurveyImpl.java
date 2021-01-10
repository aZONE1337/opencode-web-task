package ru.shchekalev.opencodewebtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shchekalev.opencodewebtask.repositories.UserSurveyRepository;
import ru.shchekalev.opencodewebtask.services.interfaces.UserSurveyService;


@Service
public class UserSurveyImpl implements UserSurveyService {

    private final UserSurveyRepository userSurveyRepository;

    @Autowired
    public UserSurveyImpl(UserSurveyRepository userSurveyRepository) {
        this.userSurveyRepository = userSurveyRepository;
    }
}
