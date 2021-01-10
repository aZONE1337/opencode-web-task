package ru.shchekalev.opencodewebtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
