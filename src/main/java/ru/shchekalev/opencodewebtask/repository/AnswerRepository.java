package ru.shchekalev.opencodewebtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shchekalev.opencodewebtask.model.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionId(Long id);
}
