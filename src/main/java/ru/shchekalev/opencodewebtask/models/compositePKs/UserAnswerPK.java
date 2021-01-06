package ru.shchekalev.opencodewebtask.models.compositePKs;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class UserAnswerPK implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "answer_id")
    private Long answerId;
}
