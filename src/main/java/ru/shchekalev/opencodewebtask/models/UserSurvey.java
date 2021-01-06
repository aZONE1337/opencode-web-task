package ru.shchekalev.opencodewebtask.models;

import lombok.Data;
import ru.shchekalev.opencodewebtask.models.compositePKs.UserSurveyPK;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_survey")
@IdClass(UserSurveyPK.class)
public class UserSurvey {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "survey_id")
    private Long surveyId;
}
