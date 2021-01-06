package ru.shchekalev.opencodewebtask.models;

import lombok.Data;
import ru.shchekalev.opencodewebtask.models.compositePKs.UserAnswerPK;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_answer")
@IdClass(UserAnswerPK.class)
public class UserAnswer {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "answer_id")
    private Long answerId;
}
