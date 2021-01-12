package ru.shchekalev.opencodewebtask.dto;

import lombok.Data;
import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.entity.Question;
import ru.shchekalev.opencodewebtask.model.entity.Survey;

import java.util.List;

@Data
public class SurveyDto {

    private Survey survey;

    private List<Question> questions;

    private List<Answer> answers;
}
