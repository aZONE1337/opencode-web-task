package ru.shchekalev.opencodewebtask.model.entity;

import lombok.Data;
import ru.shchekalev.opencodewebtask.model.security.Role;

import javax.persistence.*;
import java.util.List;

@Data
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_answer",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "answer_id") }
    )
    List<Answer> answers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_survey",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "survey_id") }
    )
    List<Survey> completedSurveys;
}
