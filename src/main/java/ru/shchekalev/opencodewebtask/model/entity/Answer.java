package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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

    @ManyToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return Objects.equals(getId(), answer.getId()) &&
                Objects.equals(getText(), answer.getText()) &&
                Objects.equals(getQuestion(), answer.getQuestion()) &&
                Objects.equals(getUsers(), answer.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getQuestion());
    }
}
