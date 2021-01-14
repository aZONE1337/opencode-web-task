package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "survey")
public class Survey implements Comparable<Survey> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean available = false;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToMany(mappedBy = "completedSurveys")
    private List<User> users;

    public boolean isValid() {
        return questions.stream().anyMatch(question -> !question.isValid());
    }

    @Override
    public int compareTo(Survey o) {
        return Long.compare(this.getId(), o.getId());
    }
}
