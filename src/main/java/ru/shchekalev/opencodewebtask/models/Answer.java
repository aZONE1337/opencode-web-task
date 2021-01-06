package ru.shchekalev.opencodewebtask.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;
}
