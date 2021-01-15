package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "availability")
    private boolean available = false;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToMany(mappedBy = "completedSurveys")
    private List<User> users;

    public boolean isValid() {
        if (questions.size() != 0)
            return questions.stream().allMatch(Question::isValid);

        return false;
    }
}
