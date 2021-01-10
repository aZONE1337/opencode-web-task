package ru.shchekalev.opencodewebtask.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_answer")
public class UserAnswer {

    @EmbeddedId
    UserAnswerPK userAnswerPK;

    @Data
    @Embeddable
    public static class UserAnswerPK implements Serializable {
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "answer_id")
        private Answer answer;
    }
}
