package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.shchekalev.opencodewebtask.model.security.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> createdSurveys;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_answer",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "answer_id") }
    )
    private Set<Answer> answers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_survey",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "survey_id") }
    )
    private Set<Survey> completedSurveys = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                getRole() == user.getRole() &&
                Objects.equals(getCreatedSurveys(), user.getCreatedSurveys()) &&
                Objects.equals(getAnswers(), user.getAnswers()) &&
                Objects.equals(getCompletedSurveys(), user.getCompletedSurveys());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getRole());
    }
}
