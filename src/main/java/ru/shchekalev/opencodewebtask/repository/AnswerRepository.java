package ru.shchekalev.opencodewebtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.model.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
