package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToMany(mappedBy = "answers")
    private List<User> users;
}
