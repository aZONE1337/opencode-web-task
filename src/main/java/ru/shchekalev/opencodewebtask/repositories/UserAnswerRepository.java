package ru.shchekalev.opencodewebtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.models.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
