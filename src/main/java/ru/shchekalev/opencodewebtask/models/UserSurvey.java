package ru.shchekalev.opencodewebtask.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_survey")
public class UserSurvey {

    @EmbeddedId
    UserSurveyPK userSurveyPK;

    @Column(name = "status")
    private AnswerStatus answerStatus = AnswerStatus.NOT_STARTED;

    @Data
    @Embeddable
    public static class UserSurveyPK implements Serializable {
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "survey_id")
        private Survey survey;
    }
}
