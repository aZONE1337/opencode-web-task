package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question implements Comparable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private boolean singleChoice = true;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public boolean isValid() {
        return answers.size() > 1;
    }

    @Override
    public int compareTo(Question o) {
        return Long.compare(this.getId(), o.getId());
    }
}
