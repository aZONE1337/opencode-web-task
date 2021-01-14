package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Data;
import ru.shchekalev.opencodewebtask.model.assistant.QuestionType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private QuestionType type = QuestionType.SINGLE;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

    public boolean isValid() {
        return answers.size() > 1;
    }
}
