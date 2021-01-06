package ru.shchekalev.opencodewebtask.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private QuestionType type;
}
