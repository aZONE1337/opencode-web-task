package ru.shchekalev.opencodewebtask.services.interfaces;

import ru.shchekalev.opencodewebtask.model.entity.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> findAll();
}
