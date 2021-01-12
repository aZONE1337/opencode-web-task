package ru.shchekalev.opencodewebtask.dto;

import lombok.Data;
import ru.shchekalev.opencodewebtask.model.entity.Answer;
import ru.shchekalev.opencodewebtask.model.assistant.QuestionType;
import ru.shchekalev.opencodewebtask.model.entity.Survey;

import java.util.Set;

@Data
public class QuestionDto {

    private String text;

    private Survey survey;

    private Set<Answer> answers;

    private QuestionType type = QuestionType.SINGLE;
}
