package ru.shchekalev.opencodewebtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
