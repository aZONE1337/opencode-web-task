package ru.shchekalev.opencodewebtask.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Availability status;
}
